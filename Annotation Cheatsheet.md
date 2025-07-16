# 📘 Spring Boot 注解速查表（Markdown 版）
用途： 快速查找 Spring Boot 中常用注解的含义与作用，便于代码编写与架构理解。
## 一、启动与配置注解
| 注解                         | 说明                                                                        | 应用场景    |
| -------------------------- | ------------------------------------------------------------------------- | ------- |
| `@SpringBootApplication`   | 核心注解，等同于 `@Configuration` + `@EnableAutoConfiguration` + `@ComponentScan` | 主类      |
| `@EnableAutoConfiguration` | 启用自动配置机制                                                                  | 启动类或配置类 |
| `@ComponentScan`           | 自动扫描指定包路径下的组件                                                             | 启动类或配置类 |
| `@Configuration`           | 表示当前类为配置类，可注册 Bean                                                        | 配置类     |
| `@Import`                  | 引入其他配置类或组件                                                                | 高级配置组合  |
## 二、组件定义注解
| 注解                | 说明                  | 典型用途        |
| ----------------- | ------------------- | ----------- |
| `@Component`      | 注册普通 Bean           | 工具类、通用组件    |
| `@Service`        | 表示服务层组件             | 业务逻辑层       |
| `@Repository`     | 数据访问层组件             | DAO、JPA 接口  |
| `@Controller`     | 控制层组件（MVC）          | 返回视图页面      |
| `@RestController` | REST 控制器，返回 JSON 数据 | RESTful API |
## 三、依赖注入注解
| 注解                        | 说明                       | 使用方式              |
| ------------------------- | ------------------------ | ----------------- |
| `@Autowired`              | 自动按类型注入 Bean             | 字段、构造器或方法         |
| `@Qualifier("beanName")`  | 与 `@Autowired` 联用，指定名称注入 | 避免歧义              |
| `@Value("${config.key}")` | 注入配置文件中的值                | 配置绑定              |
| `@Resource`               | 按名称注入（JDK 标准）            | 字段或方法             |
| `@Inject`                 | JSR-330 标准注入方式           | 与 `@Autowired` 类似 |
## 四、Web请求注解
| 注解                                                          | 说明              | 应用位置                          |
| ----------------------------------------------------------- | --------------- | ----------------------------- |
| `@RequestMapping`                                           | 映射任意 HTTP 请求路径  | 类/方法                          |
| `@GetMapping`、`@PostMapping`、`@PutMapping`、`@DeleteMapping` | 映射指定类型请求        | 方法                            |
| `@RequestParam`                                             | 获取请求参数          | 方法参数                          |
| `@PathVariable`                                             | 获取 URL 路径参数     | 方法参数                          |
| `@RequestBody`                                              | 接收请求体中的 JSON 数据 | 方法参数                          |
| `@ResponseBody`                                             | 返回 JSON 数据（非视图） | 方法或类（已被 `@RestController` 替代） |
| `@ModelAttribute`                                           | 绑定请求参数到模型对象     | 方法参数                          |
| `@CrossOrigin`                                              | 支持跨域请求          | 类/方法                          |
## 五、配置绑定与条件控制注解
| 注解                                            | 说明          | 应用位置        |
| --------------------------------------------- | ----------- | ----------- |
| `@PropertySource("classpath:xxx.properties")` | 引入外部配置文件    | 配置类         |
| `@ConfigurationProperties(prefix = "xxx")`    | 将配置项绑定为对象属性 | 配置类         |
| `@EnableConfigurationProperties`              | 启用配置属性绑定    | 启动类或配置类     |
| `@ConditionalOnProperty`                      | 配置条件满足时启用组件 | Bean 定义或配置类 |
| `@Profile("dev")`                             | 指定在某个环境下生效  | 类或方法        |
## 六、任务调度和异步执行
| 注解                         | 说明       | 说明  |
| -------------------------- | -------- | --- |
| `@EnableScheduling`        | 启用定时任务功能 | 配置类 |
| `@Scheduled(cron = "...")` | 定义定时任务   | 方法  |
| `@EnableAsync`             | 启用异步调用功能 | 配置类 |
| `@Async`                   | 声明异步执行方法 | 方法  |
## 七、测试相关注解
| 注解                   | 说明                 | 说明      |
| -------------------- | ------------------ | ------- |
| `@SpringBootTest`    | 启动完整应用上下文进行集成测试    | 测试类     |
| `@MockBean`          | 注入一个被替换的 Mock Bean | 测试类     |
| `@TestConfiguration` | 测试用配置类             | 测试类或内部类 |
## 八、数据持久化
| 注解                       | 说明          | 说明    |
| ------------------------ | ----------- | ----- |
| `@Entity`                | 声明为 JPA 实体类 | 实体类   |
| `@Table(name = "...")`   | 指定表名        | 实体类   |
| `@Id`                    | 主键字段        | 实体属性  |
| `@GeneratedValue`        | 主键生成策略      | 实体属性  |
| `@Column(name = "...")`  | 映射数据库字段     | 实体属性  |
| `@Repository`            | 表示 JPA 数据仓库 | DAO 类 |
| `@EnableJpaRepositories` | 启用 JPA 仓库功能 | 配置类   |
| `@Transactional`         | 声明事务边界      | 方法或类  |
## 九、安全控制
| 注解                                       | 说明                 | 应用位置 |
| ---------------------------------------- | ------------------ | ---- |
| `@EnableWebSecurity`                     | 启用 Spring Security | 配置类  |
| `@PreAuthorize("hasRole('ROLE_ADMIN')")` | 方法调用前进行权限校验        | 方法   |
| `@Secured("ROLE_USER")`                  | 指定角色访问权限           | 方法   |
| `@WithMockUser`                          | 测试用，模拟用户登录         | 测试类  |
