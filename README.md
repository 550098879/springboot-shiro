### SpringBoot整合Shiro安全框架

#### 步骤
- 创建 Spring Boot 应用，集成 Shiro 及相关组件，pom.xml
- 自定义 Shiro 过滤器, AccoutRealm ,Realm模块
- 配置类 ShiroConfig
- 编写认证和授权规则: 认证过滤器 , 授权过滤器

#### 认证过滤器
- anon：无需认证。
- authc：必须认证。
- authcBasic：需要通过 HTTPBasic 认证。
- user：不一定通过认证，只要曾经被 Shiro 记录即可，比如：记住我。
 
#### 授权过滤器
- perms：必须拥有某个权限才能访问。
- role：必须拥有某个角色才能访问。
- port：请求的端口必须是指定值才可以。
- rest：请求必须基于 RESTful，POST、PUT、GET、DELETE。
- ssl：必须是安全的 URL 请求，协议 HTTPS


-----------
#### 创建 3 个页面，main.html、manage.html、administrator.html
- 访问权限如下：
- 1、必须登录才能访问 main.html
- 2、当前用户必须拥有 manage 授权才能访问 manage.html
- 3、当前用户必须拥有 admin 角色才能访问 admin.html


