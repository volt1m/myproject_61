# 小药店管理系统（myproject_61）

期末考查项目：帮社区药店搞定“进销存 + 处方 + 会员”一站式管理。

---

## ✨ 主要功能
- 🔖 药品目录维护（批号、有效期、批准文号）
- 📦 进货/销售/库存自动联动，库存预警
- 📝 处方登记、药师电子签名
- 🧾 小票打印（支持 58 mm 热敏打印机）
- 🎯 会员积分与折扣
- 📊 日/月销售报表一键导出 Excel
- 🔐 权限分级：管理员、收银员、药师三种角色

---
创建数据库
CREATE DATABASE pharmacy_db CHARACTER SET utf8mb4;

导入 SQL 脚本
mysql -uroot -p pharmacy_db < sql/init.sql

修改数据源
打开 src/main/resources/application.yml，把数据库账号密码改成你自己的：
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/pharmacy_db?useSSL=false&serverTimezone=UTC
    username: root
    password: 123456
    
启动后端
./mvnw spring-boot:run   # Windows 用 mvnw.cmd spring-boot:run
默认端口 8080，启动成功后访问：
http://localhost:8080/doc.html （Knife4j 接口文档）

运行前端
cd frontend
npm install
npm run dev
浏览器打开 http://localhost:3000 即可。

🛠 技术栈
层级	技术选型
后端	Java 17 + Spring Boot 3.2
持久层	MyBatis-Plus + MySQL 8.0
安全框架	Spring Security + JWT
接口文档	Knife4j（Swagger 增强）
报表	EasyExcel（阿里）
打印	JasperReports → 58 mm 小票
前端	Vue 3 + Element Plus（独立目录）
部署	jar 包一键启动 / Docker 镜像（含 Dockerfile）

📁 项目结构
myproject_61/
├─ src/
│  ├─ main/
│  │  ├─ java/com/example/pharmacy/
│  │  │  ├─ controller/     # 控制层
│  │  │  ├─ service/        # 业务层
│  │  │  ├─ mapper/         # DAO 层
│  │  │  ├─ entity/         # 实体类
│  │  │  ├─ config/         # 安全配置、JWT、跨域
│  │  │  └─ PharmacyApplication.java
│  │  └─ resources/
│  │     ├─ application.yml
│  │     └─ mapper/*.xml      # SQL 映射
├─ sql/
│  └─ init.sql               # 初始化数据
├─ frontend/                 # Vue 前端（可选）
├─ Dockerfile
├─ pom.xml
└─ README.md

📝 使用说明（精简版）
启动后访问 http://localhost:8080/doc.html 可在线调试所有接口。
默认管理员账号：admin / 123456，登录后可在“员工管理”添加收银员/药师。
销售时扫描药品条码自动检索；无条码可手动输入拼音首字母。
库存预警阈值默认 10，可在“药品目录”里单条修改，或“批量设置”统一改。
每天营业结束后点“日结”按钮，系统自动生成当班销售额与库存变动。
更详细图文教程见 /docs/user_manual.pdf。
## 🚀 快速开始

1. 克隆代码
```bash
git clone https://github.com/volt1m/myproject_61.git
cd myproject_61

