package com.mastercard.lts.rewards.pwrautomation.dbsetup;


import org.apache.commons.lang3.RandomStringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DatabaseSetup {

    public void dataBaseConnectionSetUp(String transactionAmount, String settlementAmount, String billingAmount,String cardAcceptorName,String accountNumber,String programID,String serviceID,String acquiringInsIDCode, String cardAcceptorIDCode) throws ClassNotFoundException, SQLException {
        String query = "DECLARE\n" +
                "   AVC_SERVICE_IDENTIFIER           VARCHAR2 (250);\n" +
                "   AVC_REFNO_BANKNET_REF_NUM        VARCHAR2 (9);\n" +
                "   AVC_DE0_MTI                      VARCHAR2 (250);\n" +
                "   AVC_DE2_PRIMARY_ACCOUNT_NUMBER   VARCHAR2 (19);\n" +
                "   AVC_DE3_PROCESSING_CODE          VARCHAR2 (6);\n" +
                "   AVC_DE4_TRANSACTION_AMOUNT       VARCHAR2 (12);\n" +
                "   AVC_DE5_SETTLEMENT_AMOUNT        VARCHAR2 (12);\n" +
                "   AVC_DE6_CARDHOLDER_BILLING_AMT   VARCHAR2 (12);\n" +
                "   AVC_DE7_TRANS_DATE_AND_TIME      VARCHAR2 (10);\n" +
                "   AVC_DE11_SYS_TRACE_AUDIT_NUM     VARCHAR2 (6);\n" +
                "   AVC_DE12_TRANSACTION_TIME        VARCHAR2 (6);\n" +
                "   AVC_DE13_TRANSACTION_DATE        VARCHAR2 (32767);\n" +
                "   AVC_DE18_MERCHANT_TYPE_MCC       VARCHAR2 (4);\n" +
                "   AVC_DE20_ISSUER_COUNTRY_CD       NUMBER;\n" +
                "  AVC_DE32_ACQUIRING_INS_ID_CODE   VARCHAR2 (30);\n" +
                "  AVC_DE33_FWD_INST_ID_CD          VARCHAR2 (6);\n" +
                "   AVC_DE39_RESP_CD                 VARCHAR2 (2);\n" +
                "   AVC_DE42_CARD_ACCEPTOR_ID_CODE   VARCHAR2 (15);\n" +
                "   AVC_DE43_CARD_ACCEPTOR_NAM_LOC   VARCHAR2 (40);\n" +
                "   AVC_DE48_TCC                     VARCHAR2 (250);\n" +
                "   AVC_DE49_CUR_CODE_TRANS          VARCHAR2 (3);\n" +
                "   AVC_DE50_CUR_CODE_SETTLEMENT     VARCHAR2 (3);\n" +
                "   AVC_DE51_CUR_CODE_BILLING        VARCHAR2 (3);\n" +
                "   AVC_DE54_OTHER_AMOUNTS           VARCHAR2 (120);\n" +
                "   AVC_DE61SF13_POS_DATA            VARCHAR2 (26);\n" +
                "   AVC_DE90_ORIGINAL_DATA_ELEMENT   VARCHAR2 (42);\n" +
                "   AVC_DE95_REPLACEMENT_AMOUNT      VARCHAR2 (42);\n" +
                "  AVC_REPLY_TO_QMGR                VARCHAR2 (250);\n" +
                "   AVC_READ_FROM_QUEUE_DTTM         VARCHAR2 (32767);\n" +
                "   AVC_READ_FROM_QUEUE_CD           VARCHAR2 (1);\n" +
                "   AN_PROGRAM_ID                    NUMBER;\n" +
                "   AN_AUTH_REWARDS_SERVICE_ID       NUMBER;\n" +
                "   AVC_PATH_TXT                     VARCHAR2 (250);\n" +
                "   AVC_REQUEST_LOG_SW               VARCHAR2 (32767);\n" +
                "   AN_DE008_AMT_CRDHLDR_BILL_FEE    NUMBER;\n" +
                "   AN_DE009_CONV_RTE_SETL           NUMBER;\n" +
                "   AN_DE010_CONV_RTE_CRDHLDR_BILL   NUMBER;\n" +
                "   AN_DE022_PNT_OF_SERV_ENT_MOD     NUMBER;\n" +
                "   AN_DE038_AUTH_IDENT_RESP         VARCHAR2 (6);\n" +
                "   AN_DE061_4_POS_CRDHLDR_PRSNC     VARCHAR2 (1);\n" +
                "   AN_DE061_5_POS_CARD_PRES         NUMBER;\n" +
                "   AN_DE061_6_POS_CARD_CAP_CAP      NUMBER;\n" +
                "   AN_DE061_10_CHLR_ACT_TERM_LVL    NUMBER;\n" +
                "   AN_DE061_11_PCARD_TERM_INP_CAP   NUMBER;\n" +
                "   AN_DE061_13_POS_CNTRY_CD         NUMBER;\n" +
                "   AVC_DE061_14_POS_POST_CD         VARCHAR2 (10);\n" +
                "   AN_DE102_ACNT_IDENT_1            VARCHAR2 (28);\n" +
                "   AN_DE103_ACNT_IDENT_2            VARCHAR2 (28);\n" +
                "   avc_de060_resrv_prvt             VARCHAR2 (60);\n" +
                "   AN_RESPONSE_REASON_ID            NUMBER;\n" +
                "   AN_RTN_CD                        NUMBER;\n" +
                "   AVC_RTN_MSG                      VARCHAR2 (32767);\n" +
                "BEGIN\n" +
                "   AVC_SERVICE_IDENTIFIER := '0892';\n" +
                "   AVC_REFNO_BANKNET_REF_NUM := ?;\n" +
                "   AVC_DE0_MTI := '0120';             \n" +
                "   AVC_DE2_PRIMARY_ACCOUNT_NUMBER := ?;\n" +
                "   AVC_DE3_PROCESSING_CODE := '00';\n" +
                "   AVC_DE4_TRANSACTION_AMOUNT := ?;\n" +
                "   AVC_DE5_SETTLEMENT_AMOUNT := ?;\n" +
                "   AVC_DE6_CARDHOLDER_BILLING_AMT := ?;\n" +
                "   AVC_DE7_TRANS_DATE_AND_TIME := '10312019';\n" +
                "   AVC_DE11_SYS_TRACE_AUDIT_NUM := '010101';\n" +
                "   AVC_DE12_TRANSACTION_TIME := '050821';\n" +
                "   AVC_DE13_TRANSACTION_DATE := ?;\n" +
                "   AVC_DE18_MERCHANT_TYPE_MCC := '3412';\n" +
                "   AVC_DE20_ISSUER_COUNTRY_CD := 840;\n" +
                "   AVC_DE32_ACQUIRING_INS_ID_CODE := ?;\n" +
                "   AVC_DE33_FWD_INST_ID_CD := '123456';\n" +
                "   AVC_DE39_RESP_CD := '00';\n" +
                "   AVC_DE42_CARD_ACCEPTOR_ID_CODE := ?;\n" +
                "   AVC_DE43_CARD_ACCEPTOR_NAM_LOC := ?;\n" +
                "   AVC_DE48_TCC := 'A';\n" +
                "   AVC_DE49_CUR_CODE_TRANS := '840';\n" +
                "   AVC_DE50_CUR_CODE_SETTLEMENT := '840';\n" +
                "   AVC_DE51_CUR_CODE_BILLING := '840';\n" +
                "   AVC_DE54_OTHER_AMOUNTS := '004';\n" +
                "   AVC_DE61SF13_POS_DATA := '00045600012001319141414149';\n" +
                "   AVC_DE90_ORIGINAL_DATA_ELEMENT := '';\n" +
                "   AVC_DE95_REPLACEMENT_AMOUNT := '';\n" +
                "   AVC_REPLY_TO_QMGR := 'SMRS001D';\n" +
                "   AVC_READ_FROM_QUEUE_DTTM := sysdate;\n" +
                "   AVC_READ_FROM_QUEUE_CD := 'A';\n" +
                "   AN_PROGRAM_ID := ?;\n" +
                "   AN_AUTH_REWARDS_SERVICE_ID := ?;\n" +
                "   AVC_PATH_TXT := '2';\n" +
                "   AVC_REQUEST_LOG_SW := 'Y';\n" +
                "   AN_DE008_AMT_CRDHLDR_BILL_FEE := '88888888';\n" +
                "   AN_DE009_CONV_RTE_SETL := '99999999';\n" +
                "   AN_DE010_CONV_RTE_CRDHLDR_BILL := '10101010';\n" +
                "   AN_DE022_PNT_OF_SERV_ENT_MOD := '222';\n" +
                "   AN_DE038_AUTH_IDENT_RESP := '38D38D';\n" +
                "   AN_DE061_4_POS_CRDHLDR_PRSNC := '4';\n" +
                "   AN_DE061_5_POS_CARD_PRES := '5';\n" +
                "   AN_DE061_6_POS_CARD_CAP_CAP := '6';\n" +
                "   AN_DE061_10_CHLR_ACT_TERM_LVL := '1';\n" +
                "   AN_DE061_11_PCARD_TERM_INP_CAP := '2';\n" +
                "   AN_DE061_13_POS_CNTRY_CD := '840';\n" +
                "   AVC_DE061_14_POS_POST_CD := '9141414149';\n" +
                "   AN_DE102_ACNT_IDENT_1 := '102102';\n" +
                "   AN_DE103_ACNT_IDENT_2 := '103103';\n" +
                "   avc_de060_resrv_prvt := NULL;\n" +
                "   AN_RESPONSE_REASON_ID := NULL;\n" +
                "   AN_RTN_CD := NULL;\n" +
                "   AVC_RTN_MSG := NULL;\n" +
                "   PKG_AUTH_SERVICE.PRC_SET_DEBUG(TRUE); \n" +
                "\n" +
                "   PKG_AUTH_SERVICE.PRC_RTR_AUTH_SERVICE_MSG (\n" +
                "      AVC_SERVICE_IDENTIFIER,\n" +
                "      AVC_REFNO_BANKNET_REF_NUM,\n" +
                "      AVC_DE0_MTI,\n" +
                "      AVC_DE2_PRIMARY_ACCOUNT_NUMBER,\n" +
                "      AVC_DE3_PROCESSING_CODE,\n" +
                "      AVC_DE4_TRANSACTION_AMOUNT,\n" +
                "      AVC_DE5_SETTLEMENT_AMOUNT,\n" +
                "      AVC_DE6_CARDHOLDER_BILLING_AMT,\n" +
                "      AVC_DE7_TRANS_DATE_AND_TIME,\n" +
                "      AVC_DE11_SYS_TRACE_AUDIT_NUM,\n" +
                "      AVC_DE12_TRANSACTION_TIME,\n" +
                "      AVC_DE13_TRANSACTION_DATE,\n" +
                "      AVC_DE18_MERCHANT_TYPE_MCC,\n" +
                "      AVC_DE20_ISSUER_COUNTRY_CD,\n" +
                "      AVC_DE32_ACQUIRING_INS_ID_CODE,\n" +
                "      AVC_DE33_FWD_INST_ID_CD,\n" +
                "      AVC_DE39_RESP_CD,\n" +
                "      AVC_DE42_CARD_ACCEPTOR_ID_CODE,\n" +
                "      AVC_DE43_CARD_ACCEPTOR_NAM_LOC,\n" +
                "      AVC_DE48_TCC,\n" +
                "      AVC_DE49_CUR_CODE_TRANS,\n" +
                "      AVC_DE50_CUR_CODE_SETTLEMENT,\n" +
                "      AVC_DE51_CUR_CODE_BILLING,\n" +
                "      AVC_DE54_OTHER_AMOUNTS,\n" +
                "      AVC_DE61SF13_POS_DATA,\n" +
                "    AVC_DE90_ORIGINAL_DATA_ELEMENT,\n" +
                "      AVC_DE95_REPLACEMENT_AMOUNT,\n" +
                "      AVC_REPLY_TO_QMGR,\n" +
                "     AVC_READ_FROM_QUEUE_DTTM,\n" +
                "     AVC_READ_FROM_QUEUE_CD,\n" +
                "      AN_PROGRAM_ID,\n" +
                "      AN_AUTH_REWARDS_SERVICE_ID,\n" +
                "      AVC_PATH_TXT,\n" +
                "      AVC_REQUEST_LOG_SW,\n" +
                "      AN_DE008_AMT_CRDHLDR_BILL_FEE,\n" +
                "      AN_DE009_CONV_RTE_SETL,\n" +
                "      AN_DE010_CONV_RTE_CRDHLDR_BILL,\n" +
                "      AN_DE022_PNT_OF_SERV_ENT_MOD,\n" +
                "      AN_DE038_AUTH_IDENT_RESP,\n" +
                "      AN_DE061_4_POS_CRDHLDR_PRSNC,\n" +
                "      AN_DE061_5_POS_CARD_PRES,\n" +
                "      AN_DE061_6_POS_CARD_CAP_CAP,\n" +
                "      AN_DE061_10_CHLR_ACT_TERM_LVL,\n" +
                "      AN_DE061_11_PCARD_TERM_INP_CAP,\n" +
                "      AN_DE061_13_POS_CNTRY_CD,\n" +
                "      AVC_DE061_14_POS_POST_CD,\n" +
                "      AN_DE102_ACNT_IDENT_1,\n" +
                "      AN_DE103_ACNT_IDENT_2,\n" +
                "      avc_de060_resrv_prvt,\n" +
                "      AN_RESPONSE_REASON_ID,\n" +
                "      AN_RTN_CD,\n" +
                "      AVC_RTN_MSG);\n" +
                "   COMMIT;\n" +
                "\n" +
                "   DBMS_OUTPUT.PUT_LINE ('AN_RESPONSE_REASON_ID: ' || AN_RESPONSE_REASON_ID);\n" +
                "   DBMS_OUTPUT.PUT_LINE ('AN_RTN_CD: ' || AN_RTN_CD);\n" +
                "   DBMS_OUTPUT.PUT_LINE ('AVC_RTN_MSG: ' || AVC_RTN_MSG);\n" +
                "END;\n";
        Class.forName("oracle.jdbc.OracleDriver");
        try (
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@//mrs-mtf.mastercard.int:1527/MRS_WEB_SERV","MRS_BATCH", "batch123");
                PreparedStatement ps= connection.prepareStatement(query);
        )
        {
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date today = Calendar.getInstance().getTime();
            String todayDate = df.format(today);
            char[] date = todayDate.toCharArray();
            char delimiter ='/';
            StringBuilder stringBuilder = new StringBuilder();
            for(char c:date)
            {
                if(c!=delimiter)
                {
                    stringBuilder.append(c);
                }
            }
            String transactionDate = stringBuilder.toString();
            String bankRefNum = RandomStringUtils.randomNumeric(9);
            ps.setString(1,bankRefNum);
            ps.setString(2,accountNumber);
            ps.setString(3,transactionAmount);
            ps.setString(4,settlementAmount);
            ps.setString(5,billingAmount);
            ps.setString(6,transactionDate);
            ps.setString(7,acquiringInsIDCode);
            ps.setString(8,cardAcceptorIDCode);
            ps.setString(9,cardAcceptorName);
            ps.setString(10,programID);
            ps.setString(11,serviceID);
            ps.execute();
        }
    }

}
