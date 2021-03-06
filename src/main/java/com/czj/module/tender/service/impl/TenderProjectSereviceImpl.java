package com.czj.module.tender.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czj.module.DESCBCTest;
import com.czj.module.tender.entity.TenderNo;
import com.czj.module.tender.entity.TenderProject;
import com.czj.module.tender.entity.TenderSysLog;
import com.czj.module.tender.mapper.TenderProjectMapper;
import com.czj.module.tender.service.ITenderNoService;
import com.czj.module.tender.service.ITenderProjectService;
import com.czj.module.tender.service.ITenderSysLogService;
import com.czj.module.tender.util.TenderXmlUtil;
import freemarker.template.utility.StringUtil;
import org.apache.axis.client.Call;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.*;

/**
 * @Author:caizhijian
 * @Date:2021-07-20
 */
@Service("tenderProjectService")
public class TenderProjectSereviceImpl extends ServiceImpl<TenderProjectMapper, TenderProject> implements ITenderProjectService {

    private static Logger logger = LogManager.getLogger(TenderProjectSereviceImpl.class);
    //注释掉地址
    private static final String endpoint = "";

    @Autowired
    private ITenderNoService tenderNoService;

    @Autowired
    private ITenderSysLogService tenderSysLogService;

    /**
     * 根据数据类型获取项目编号
     * @param type 数据类型
     * @throws Exception
     */
    @Override
    public List<String> getTenderNoByType(String startStr, String endStr, String type) throws Exception {

        //读取同步记录表数据

        org.apache.axis.client.Service service = new org.apache.axis.client.Service();
        Call call;

        List<String> tenderNos = new ArrayList();
        try {
            call=(Call)service.createCall();
            call.setTargetEndpointAddress(endpoint);//远程调用路径
            call.setOperationName(new QName("http://tempuri.org/","ReadTenderNodata"));//调用的方法名
            //设置参数名:

            call.addParameter(new QName("http://tempuri.org/","userToken"), //参数名
                    org.apache.axis.encoding.XMLType.XSD_STRING,//参数类型:String
                    javax.xml.rpc.ParameterMode.IN);//参数模式：'IN' or 'OUT'

            call.addParameter(new QName("http://tempuri.org/","startTime"), //参数名
                    org.apache.axis.encoding.XMLType.XSD_STRING,//参数类型:String
                    javax.xml.rpc.ParameterMode.IN);//参数模式：'IN' or 'OUT'

            call.addParameter(new QName("http://tempuri.org/","endTime"), //参数名
                    org.apache.axis.encoding.XMLType.XSD_INT,//参数类型:INT
                    javax.xml.rpc.ParameterMode.IN);//参数模式：'IN' or 'OUT'
            //设置返回值类型：
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//返回值类型：String
            call.setUseSOAPAction(true);
            call.setSOAPActionURI("http://tempuri.org/ReadTenderNodata");

            String userToken = "33C240A6-5349-428E-93EE-5397C679A6BD";
            byte[] startBytes = startStr.getBytes("UTF-8");
            byte[] endBytes = endStr.getBytes("UTF-8");
            String startDateTime = new BASE64Encoder().encodeBuffer(DESCBCTest.desEncodeCBC(startBytes));
            String endDateTime = new BASE64Encoder().encodeBuffer(DESCBCTest.desEncodeCBC(endBytes));
            //调用接口获取数据
            String result = (String)call.invoke(new Object[]{userToken,startDateTime,endDateTime});//远程调用
            //得到响应数据
            byte[] resultBytes = new BASE64Decoder().decodeBuffer(result);
            String s = new String(DESCBCTest.desDecodeCBC(resultBytes) ,"UTF-8");
            logger.info("tenderNoXml:{}",s);
            if("暂无数据".equals(s)){
                return tenderNos;
            }
            //创建并加载xml
            Document document= DocumentHelper.parseText(s);

            //获取根节点
            Element rootElement = document.getRootElement();
            Iterator iterator = rootElement.elementIterator();
            while (iterator.hasNext()){
                Element table = (Element) iterator.next();
                Element dataType = table.element("DataType");

                if(dataType!=null && type.equals(dataType.getStringValue())){
                    Element tenderNo = table.element("TenderNo");
                    if(null!=tenderNo){
                        tenderNos.add(tenderNo.getStringValue());
                    }
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return tenderNos;
    }


    /**
     * 查询项目信息
     * @param tenderNo 项目编号
     * @return
     */
    public String getTenderProject(String tenderNo){
        //调用接口获取数据
        String result = null;
        org.apache.axis.client.Service service = new org.apache.axis.client.Service();
        Call call;
        try {
            call=(Call)service.createCall();
            call.setTargetEndpointAddress(endpoint);//远程调用路径
            call.setOperationName(new QName("http://tempuri.org/","ReadTenderdata"));//调用的方法名
            //设置参数名:

            call.addParameter(new QName("http://tempuri.org/","userToken"), //参数名
                    org.apache.axis.encoding.XMLType.XSD_STRING,//参数类型:String
                    javax.xml.rpc.ParameterMode.IN);//参数模式：'IN' or 'OUT'

            call.addParameter(new QName("http://tempuri.org/","TenderNo"), //参数名
                    org.apache.axis.encoding.XMLType.XSD_STRING,//参数类型:String
                    javax.xml.rpc.ParameterMode.IN);//参数模式：'IN' or 'OUT'

            //设置返回值类型：
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//返回值类型：String
            call.setUseSOAPAction(true);
            call.setSOAPActionURI("http://tempuri.org/ReadTenderdata");

            String userToken = "33C240A6-5349-428E-93EE-5397C679A6BD";
            byte[] bytes = tenderNo.getBytes("UTF-8");
            String tenderNoData = new BASE64Encoder().encodeBuffer(DESCBCTest.desEncodeCBC(bytes));
            result = (String)call.invoke(new Object[]{userToken,tenderNoData});//远程调用
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public void sysTenderProject(String start, String end) throws Exception {

        //查询项目编号
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        while(startDate.isBefore(endDate)){
            LocalDate localDate = startDate.plusDays(9);
            System.out.println("startDate:"+startDate.toString()+",endDate:"+localDate.toString());

            List<String> tenderNos = getTenderNoByType(startDate.toString(), localDate.toString(), "-1");
            for(String tenderNo : tenderNos){
                //根据项目编号查询项目信息
                String originalData = getTenderProject(tenderNo);
                if(StringUtils.isBlank(originalData)){
                    continue;
                }

                //得到响应数据
                //String originalData = "kndlgNL2IbXQ8vDqraptxiZJiG3b/zm11oCNaUInKKPBIayzcQ1vg/tEjSrn/UQohdqfQmskGt9K2tFpecpl+uPs5nRJBDQukavC7ZKy7r8zaPL4WA3ZnkvLb6JA9+BtPH6fOg/aUSbkxjAC3fOJ2Y2grcMiTFMniKMFFR4bEjDgwXF1vkajkue3K1247NZUFfX9YnHe5f+VYwQgrUVZyAVXE48RmMJXmA80ZlYYlGBkfP4ahHymRgGjb2sbgxGbTL2oKdK1DnnRUd2eMe+h9IRILBUsrYr/OkjHX7bUslzCJKtcm/NVTbhpODagCbbawFwDG4jua/feQKPMtgHSUoNYlHjXTlZ7vQYNgPt4NQIQHe3dVuvK3Dq+sPShNQFUhXfxWQ4uCnY2S0NfM09aejj7t40c8DXpiNQkGq2OLl9fOlYZk/5YTZXXn2TJ1KGjJ4z6hqGDO0KMoWjeqAQ/rC6hXeoa5tg2zyPL1JjZ7H8rIr7kmLTd70DQUv/fXa2XXrfoeZCk4Q5Urra/BXb22iRdvIdGEvWzaem0Z6FID9VMi9WXgm/OTqdXmUo7OAV2JxWRFYTpRvkDQNV+SK9SXs21eCOTpTvFe69jXkeJ3Loiw7fM5aM2Tvz1kBo0Ja1t30xPmCzR2Cq0q4iqgtK6G82i+vFb7Ky0yANcwLpUn0owc2NsoC7fAbyVcEguAD5YQRf2Db2stpBZAPZbarFd41YSt2UZ9+x3zI+0Nv5QHISpxEAVJ8ELLSPStICq8UOlujdj72Tcpfk+8Qk7jDXaiN0BFOVc521nyMnG95x9fUYTZj2XQ0kcc7txsK6/4aJC7mwuSaZGKE1HAQZejv1cyvwaViWXuTHnfuvH5Ta3h3YcxI91ohNMOdkMGfHIOc2fQqXOFOx+49JMogAfhaw12eCk8H3iawBKhOxkXJJDLJLZFlzpDHBBAm/jrWtZMO2bhNxLxTk/i0NtXwtIogpXikFq9hK0KrqqE+qu3K1INIDtupvw63OW4DFpa+BlW31YdSRSq7jABI3Gf04FkivSd0B0SYHw92QvUgq+0UkFw1yH9Q/zUA/dRMG7cLOU+WCkFfs26Rmoa1YiP7IjVjfqRt3d8BP/+mUsPncNZx2KgxvzyGxxXYKHG6wH4gVCWiuLdEtEUEa0KsbuN2hYpJKb52MmM9g8AGVosd2c1WpUMd9pG8N25kGhgWkePvREO4cIaeocGXwnwrHCEj7X29UXbh7g7wSofECisMdhNoVC4dGkCUVQU1F+aBXTLlY3scKlnpE0iHt7w0WrQ9PiRifnjSRW5oGv5TlNGZnqCqSe6rD+USRJPkY9IyC9YV4XgWmdGIZCaIL0f3xpqY0jJx+0rtCPi58gUbvheFHU7INdignDlmGiYyrGlVV+9m8lMlnzuklrAMSZ72xCdxkGrHjHz9BsiNs8FhLUMuivMVY3crn7rnuCBrVEmlutyzkyCWxubQBpVg8xlMzCMi73JIlG/Vednyn9CD2VOQ7UJavAF7Bmt178i5AfXZQYc3eCFM7LN0GMKMajn/ttUkhMrKo9I5DO5Ld8ExhMR6sZVw6YXYggsWGnudkgpEQgEnHKUH08OOmlarVtHwMBaqfJLMHalQ3e2jaxW0WHW8+cVuaT+gnpPc9cPZ/r9toIGAPcFCMXe1SnXw1fzq99fK+84SYnE6QdszIe5mwrUnvlvjGedZSAsVVIiwNAndB5V0J7W2HcJf3sDgqc1mSoegQc9wYIQQrDez4Uy0hgPpCUuEe5VFAZmRkLthz3wmsKudoeE9MKXe2F72S4KoyeXw5TxrawnX0Xb3lLyCVpA8juS2jCN8450MzblaVNyXOITGUo3wkQBAmffHviPMDJhtNbL+3FEBrfMqvtaMbd0RgiQoJbwq1lnBwJ3lVMH4XM88lKrct27YkPbQO4Pmr2B22/HzCK0VMG0OoyNbO4ZyinFzCHywoAuYuPBcqkOMydtXKIQ4Rp2y2VqTBe+v/6LuXcobxvdLmJqyHvsfTyjn2ncfTGYDqsfj8G+2rvRQqsww3uKQ/sJPKIaQB6wUO3O5kIP91vX7v9elWd/r/aYLfD7yfk7arZfIRz8Idn/GVsk5rNJS+7PqY5MOJacoxBhKUXAUD7vTm26SXid9WZF2aRcERup8g73JV+0eHTIaa7a4YMsIf7EYR2Q3VyIxXYGJJikjfmp7CyBtRvolf20sG4k2w4RJkJ9JsaHL0Sjx0zJSs6JckO4ODavcXXi5JCBmW9H4V+POxDf43Nnxo/gTLM+vEiUi9jm1ZI4ZdIoXS928ccuBIXfaLIBg6d1aaMC9KIeeedTD2HS4gdoKwr+xHQ3h6QbMdNOrfd0Hx9AQ==";
                byte[] resultBytes = new BASE64Decoder().decodeBuffer(originalData);
                //解密编码后的结果
                String resultData = new String(DESCBCTest.desDecodeCBC(resultBytes), "UTF-8");
                logger.info("tenderProject:{}",resultData);
                Map<String, Class> mapClass = new HashMap<String, Class>();
                mapClass.put("Table", TenderProject.class);
                //投标项目信息
                TenderProject project = (TenderProject) TenderXmlUtil.xmlStrToBean(resultData, mapClass);
                Boolean sysResult = true;
                String resultLog = null;
                try {
                    //保存项目信息
                    boolean b = this.saveOrUpdate(project);
                    System.out.println(b);
                }catch (Exception e){
                    sysResult = false;
                    String eStr = e.toString();
                    resultLog = eStr.length()>2000?eStr.substring(0, 2000):eStr;
                }

                //保存响应信息
                TenderSysLog sysLog = new TenderSysLog();
                sysLog.setTenderNo(tenderNo);
                sysLog.setSysResult(sysResult);
                sysLog.setSysResultLog(resultLog);
                sysLog.setSysResultData(resultData.length()>3000?resultData.substring(0, 3000):resultData);
                sysLog.setCreateTime(new Date());
                tenderSysLogService.save(sysLog);

                //保存项目编号
                TenderNo tenderNoEntry = new TenderNo();
                tenderNoEntry.setTenderNo(tenderNo);
                tenderNoEntry.setDataType("-1");
                tenderNoService.save(tenderNoEntry);

            }
            startDate = localDate.plusDays(1);
        }
    }
}
