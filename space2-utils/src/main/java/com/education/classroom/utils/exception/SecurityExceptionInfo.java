package com.education.classroom.utils.exception;

public class SecurityExceptionInfo extends ErrorInfo{ // To be modified

    // Error Message for LDAP Base
    public static String LDAP_CONFIG_INIT_ERROR = "目录服务器配置初始化错误!";
    public static String LDAP_QUERY_INIT_ERROR = "目录服务器查询初始化错误!";
    public static String LDAP_MODIFY_INIT_ERROR = "目录服务器修改初始化错误!";
    public static String LDAP_QUERY_OPERATION_CONTROL_ERROR = "目录服务器查询控件错误!";
    public static String LDAP_QUERY_FILTER_SIZE_LIMIT_ERROR = "查询条件过长，请缩小范围!";
    
    public static String LDAP_MASTER_NOT_AVAILABLE_ERROR = "LDAP主服务器不能连接，不能执行修改操作!";
    public static String SERVICE_NOT_AVAILABLE_ERROR = "网络连接或者目录服务连接错误!";

    // Error Message for OrgQuery
    public static String ORG_QUERY_ORGDN_ERROR = "传入的组织机构全路径名称有错!";

    // Error Message for OrgModify
    public static String ORG_MODIFY_LEVEL_ERROR = "组织机构层级Level属性错误!";
    public static String ORG_MODIFY_SYSTEM_ERROR = "组织机构修改系统错误，请联系管理员!";
    public static String ORG_MODIFY_INVALID_ORGDN = "错误的组织机构DN，请联系管理员!";
    public static String ORG_MODIFY_TITLE_ADD_SYSTEMUSER_ERROR = "系统用户不能增加职务!";
    public static String ORG_MODIFY_TITLE_ADD_ALREADYEXIST_ERROR = "用户已经有此组织结构的此职务，不能重复增加!";
    public static String ORG_MODIFY_TITLE_REMOVE_NOT_EXIST_ERROR = "用户没有此组织结构的此职务，删除出错!";
    public static String ORG_MODIFY_TITLE_REMOVE_SYSTEMUSER_ERROR = "系统用户没有职务，不能删除!";
    public static String ORG_MODIFY_MEMBERS_NOT_EXIST_ERROR = "传入的用户集合中有的用户不存在!";
    public static String ORG_MODIFY_MANAGER_OPERATOR_NOT_EXIST_ERROR = "传入的组织机构领导，管理员或者主管领导不存在!";
    public static String ORG_MODIFY_OWNEROFFICE_VIOLIATION_ERROR = "主管领导只能在行政组织机构中的处级定义!";
    public static String ORG_MODIFY_GROUP_CANT_REMOVE_ERROR = "领导，角色小组不能删除!";
    public static String ORG_MODIFY_ROOT_CANT_REMOVE_ERROR = "根目录结构不能删除!";
    public static String ORG_MODIFY_ORG_DUPLICATE_ERROR = "组织机构重复，相同组织机构已经存在!";
    public static String ORG_MODIFY_NULL_PARA_ERROR = "组织机构修改错误，传入参数有必须字段为空值或者无效!";
    public static String ORG_MODIFY_USER_NOT_VALID = "组织机构修改错误，传入人员为已经无效!";
    public static String ORG_MODIFY_ORGNUMBER_ERROR = "组织机构修改错误，组织机构代码为空，无效或者重复!";
    public static String ORG_MODIFY_DEFAULT_LIB_ERROR = "组织机构修改错误，缺省归档库无效，必须为有效的库ID!";
    public static String ORG_MODIFY_OU_ERROR = "组织机构修改错误，唯一编码（OU）为空，无效或者重复!";
    public static String ORG_MODIFY_ADD_UNDER_SYSTEM_ERROR = "不能在系统产生的组织机构下新建组织机构!";
    public static String ORG_MODIFY_ADD_PARENT_NOT_EXIST_ERROR = "父组织机构不存在，不能增加！";
    public static String ORG_MODIFY_FOREIGN_DN_ERROR = "外部单位组织机构DN无效！";
    
    public static String ORG_REMOVE_NOT_EXIST_ERROR = "组织机构不存在，无需删除！";
    public static String ORG_REMOVE_MEMBER_TITLES_ERROR = "删除组织机构中用户的职称出错！";
    public static String ORG_REMOVE_VIRTUAL_USER_REMOVE_ERROR = "组织机构删除错误，公办账户无法删除！";
    public static String ORG_ADD_VIRTUAL_USER_EXIST_ERROR = "组织机构新增错误，公办账户已经存在，无法创建!可以尝试更换组织机构代码。";
    public static String ORG_ADD_VIRTUAL_USER_ADD_ERROR = "组织机构新增错误，公办账户创建时发生错误!";

    public static String ORG_QUERY_DATA_CONFLICT_ERROR = "子组织机构的有效性或者CNNP归属性发生冲突!";

    // Error Message for UserModify
    public static String USER_MODIFY_SYSTEM_ERROR = "用户修改系统错误，请联系管理员!";
    public static String USER_MODIFY_CANTREMOVE_ERROR = "系统用户不能删除!";
    public static String USER_MODIFY_SYSTEMUSER_ERROR = "系统用户不能修改!";
    public static String USER_MODIFY_NULL_PARA_ERROR = "用户信息修改错误，传入参数有必须字段为空值或者无效";
    public static String USER_SITE_ID_MISMATCH = "用户站点属性错误，只能增加本站点的用户!";
    public static String USER_PLANT_ID_MISMATCH = "用户电厂属性错误，此用户不在此电厂下的任何组织机构中!";
    
    public static String USER_MODIFY_DUPLICATE_ERROR = "用户重复，相同的用户已经存在";
    public static String USER_MODIFY_TAM_ERROR = "用户SSO信息修改错误，本次修改不能删除";

    // Error Message for RoleModify
    public static String APPROLE_MODIFY_NULL_PARA_ERROR = "应用角色修改错误，传入参数有必须字段为空值或者无效";
    public static String APPROLE_MODIFY_MEMBERS_NOT_EXIST_ERROR = "应用角色成员增加中，传入的用户集合中有的用户不存在!";
    public static String APPROLE_MODIFY_SYSTEM_ERROR = "应用角色修改系统错误，请联系管理员!";
    public static String APPROLE_MODIFY_ROLE_ALREADY_EXIST = "应用角色已经存在!";
    public static String APPROLE_MODIFY_LEVEL_ERROR = "应用角色修改错误，不能直接增加部门角色成员，请在组织机构中对应的角色小组进行增加";

    // Error Message for Query
    public static String QUERY_NULL_PARA_ERROR = "传入的查询参数为空值或者无效!";
    public static String COMMON_FILE_READ_ERROR = "读取文件出现错误！";
    public static String COMMON_FILE_WRITE_ERROR = "写入文件出现错误！";

    public static String DICTIONAARY_SERVICE_ERROR = "字典数据操作发生错误！";
    public static String NOT_AUTHORIZED_ERROR = "没有权限进行操作！";
}
