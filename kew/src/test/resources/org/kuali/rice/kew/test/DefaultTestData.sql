insert into KREW_APP_CNST_T (APPL_CNST_NM, VAL, VER_NBR) values ('Workflow.AdminWorkgroup', 'WorkflowAdmin', 1)
;
insert into KREW_APP_CNST_T (APPL_CNST_NM, VAL, VER_NBR) values ('Workgroup.IsRouteLogPopup', 'false', 0)
;
insert into KRNS_NMSPC_T (NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) VALUES('KR-NS', '53680C68F595AD9BE0404F8189D80A6C', 1, 'Kuali Nervous System', 'Y')
;
insert into KRNS_NMSPC_T (NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) VALUES('KR-WKFLW', '5C730C2EFF5D446AE0404F8189D85717', 0, 'Workflow', '1')
;
insert into KRNS_PARM_TYP_T (PARM_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) VALUES('AUTH', '53680C68F593AD9BE0404F8189D80A6C', 1, 'Authorization', 'Y')
;
insert into KRNS_PARM_TYP_T (PARM_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) VALUES('CONFG', '53680C68F591AD9BE0404F8189D80A6C', 1, 'Config', 'Y')
;
insert into KRNS_PARM_TYP_T (PARM_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) VALUES('HELP', '53680C68F594AD9BE0404F8189D80A6C', 1, 'Help', 'Y')
;
insert into KRNS_PARM_TYP_T (PARM_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) VALUES('VALID', '53680C68F592AD9BE0404F8189D80A6C', 1, 'Document Validation', 'Y')
;
insert into KRNS_PARM_DTL_TYP_T (NMSPC_CD, OBJ_ID, PARM_DTL_TYP_CD, NM) VALUES ('KR-WKFLW', '18695E69ED0D4FBE8B084FCA8066D21C', 'DocumentSearch', 'Document Search ')
;
insert into KRNS_PARM_DTL_TYP_T (NMSPC_CD, PARM_DTL_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) VALUES('KR-NS', 'All', '53680C68F596AD9BE0404F8189D80A6C', 1, 'All', 'Y')
;
insert into KRNS_PARM_DTL_TYP_T (NMSPC_CD, PARM_DTL_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) VALUES('KR-WKFLW', 'Rule', 'FC831215ED534549845BCE2C59B16FD9', 1, 'Rule', 'Y')
;
insert into KRNS_PARM_DTL_TYP_T (NMSPC_CD, PARM_DTL_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND) VALUES('KR-NS', 'Lookup', '53680C68F599AD9BE0404F8189D80A6C', 1, 'Lookup', 'Y')
;
insert into KRNS_PARM_T (NMSPC_CD, OBJ_ID, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM) VALUES ('KR-WKFLW', 'E78100F6F14C4932B54F7719FA5C27E9', 'DocumentSearch', 'DOCUMENT_SEARCH_POPUP_IND', 'CONFG', 'Y', 'desc', 'A', 'WorkflowAdmin')
;
insert into KRNS_PARM_T (NMSPC_CD, OBJ_ID, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM) VALUES ('KR-WKFLW', '632680DDE9A7478CBD379FAF90C7AE72', 'DocumentSearch', 'DOCUMENT_SEARCH_ROUTE_LOG_POPUP_IND', 'CONFG', 'N', 'desc', 'A', 'WorkflowAdmin')
;
INSERT INTO KRNS_PARM_T (NMSPC_CD, OBJ_ID, PARM_DTL_TYP_CD, PARM_NM, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM) VALUES ('KR-WKFLW', 'E05A692D62E54B87901D872DC37208A1', 'Rule', 'CACHING_IND', 'CONFG', 'Y', 'desc', 'A', 'WorkflowAdmin')
;
insert into KRNS_PARM_T (NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD, GRP_NM, ACTV_IND) VALUES('KR-NS', 'Lookup', 'RESULTS_LIMIT', '53680C68F5A8AD9BE0404F8189D80A6C', 1, 'CONFG', '200', 'Maximum number of results returned in a look-up query.', 'A', 'WorkflowAdmin', 'Y')
;
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD) VALUES ('1', '5B97C50B02FF6110E0404F8189D85213', 1, 'Default', Null, 'Y', 'KUALI')
;
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD) VALUES('7', '5ADF18B6D4C07954E0404F8189D85002', 1, 'TestType', 'responsibilityResponsibilityTypeService', 'Y', 'KR-WKFLW')
;
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, SRVC_NM, ACTV_IND, NMSPC_CD, CMPNT_NM, APPL_URL) VALUES('13', '5ADF18B6D4947954E0404F8189D85002', 1, 'documentTypeName', Null, Null, 'Y', 'KR-WKFLW', 'org.kuali.rice.kim.bo.impl.KimAttributes', Null)
;
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, SRVC_NM, ACTV_IND, NMSPC_CD, CMPNT_NM, APPL_URL) VALUES('16', '5ADF18B6D4977954E0404F8189D85002', 1, 'routeNodeName', Null, Null, 'Y', 'KR-WKFLW', 'org.kuali.rice.kim.bo.impl.KimAttributes', Null)
;
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, SRVC_NM, ACTV_IND, NMSPC_CD, CMPNT_NM, APPL_URL) VALUES('40', '5C4970B2B2DF8277E0404F8189D80B30', 1, 'required', Null, Null, 'Y', 'KR-WKFLW', 'org.kuali.rice.kim.bo.impl.KimAttributes', Null)
;
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, SRVC_NM, ACTV_IND, NMSPC_CD, CMPNT_NM, APPL_URL) VALUES('41', '5C4970B2B2E08277E0404F8189D80B30', 1, 'actionDetailsAtRoleMemberLevel', Null, Null, 'Y', 'KR-WKFLW', 'org.kuali.rice.kim.bo.impl.KimAttributes', Null)
;
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND) VALUES('7', '5ADF18B6D4C17954E0404F8189D85002', 1, 'a', '7', '13', 'Y')
;
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND) VALUES('8', '5ADF18B6D4C27954E0404F8189D85002', 1, 'b', '7', '16', 'Y')
;
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND) VALUES('80', '5C4970B2B2E18277E0404F8189D80B30', 1, 'c', '7', '40', 'Y')
;
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND) VALUES('81', '5C4970B2B2E28277E0404F8189D80B30', 1, 'd', '7', '41', 'Y')
;