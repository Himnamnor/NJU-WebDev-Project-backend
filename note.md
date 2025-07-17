# 开发和学习笔记
## PO类型开发
### javax.persistence和jakarta.persistence区别
- Spring Boot 3.x版本开始，默认使用jakarta.persistence包。
- javax和jakarta的相互修改主要通过包名，注意可能有方法名的改变
### JPA注解
- `@Entity`：实体类注解，标识一个类是JPA实体。
- `@Column`，在类中定义属性时使用，指定数据库表的列名。
- `@Id`：主键注解，标识实体的主键属性。
- `@GeneratedValue`：
  - `strategy`：指定主键生成策略，如`GenerationType.IDENTITY`、`GenerationType.SEQUENCE`等。
  - `generator`：指定主键生成器的名称。
- `@Enumerated`：枚举类型注解，指定枚举属性的存储方式。
  - `EnumType.ORDINAL`：按枚举的顺序存储。
  - `EnumType.STRING`：按枚举的名称存储。
### Jpa接口
| 前缀                            | 作用                 | 示例                             |
| ----------------------------- | ------------------ | ------------------------------ |
| `findBy` / `readBy` / `getBy` | 查询数据               | `findByUserName(String name)`  |
| `countBy`                     | 统计数量               | `countByStatus(String status)` |
| `existsBy`                    | 是否存在（返回 boolean）   | `existsByEmail(String email)`  |
| `deleteBy` / `removeBy`       | 删除数据               | `deleteByUserId(Long id)`      |
| `queryBy`（较少用）                | 查询数据（等价于 `findBy`） | `queryByRole(String role)`     |


## Jakarta Filter实现
Jakarta Filter接口声明如下
```java
package jakarta.servlet;
import java.io.IOException;
public interface Filter {
    void init(FilterConfig filterConfig) throws ServletException;
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException;
    void destroy();
}
```