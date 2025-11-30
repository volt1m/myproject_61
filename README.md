# 房地产管理系统 (Real Estate Management System)

## 项目简介

一个基于 Web 的房地产信息管理系统，提供房产信息的增删查改功能。项目采用现代前后端分离架构，界面简洁美观，操作便捷。

**项目名称**: myproject_61  
**作者**: 李萌  
**学号**: 2022131061 
**许可证**: MIT License

## 功能特性

### 🏠 核心功能
- **房产信息管理**: 完整的 CRUD（增删查改）操作
- **房源浏览**: 直观的房产列表和详情展示
- **搜索过滤**: 支持按价格、面积、位置等多条件筛选
- **数据统计**: 房产数据可视化分析

### 💡 技术特色
- 响应式设计，支持多设备访问
- 数据持久化存储
- 用户友好的操作界面
- 模块化代码结构，易于维护扩展

## 技术栈

### 前端技术
- HTML5 + CSS3 + JavaScript (ES6+)
- Bootstrap 5 - 响应式UI框架
- Chart.js - 数据可视化
- Font Awesome - 图标库

### 后端技术
- Node.js + Express.js - 服务器框架
- MySQL - 数据库管理系统
- RESTful API - 接口设计规范

### 开发工具
- Git - 版本控制
- VS Code - 开发环境
- Postman - API测试

## 项目结构

```
myproject_61/
├── src/
│   ├── controllers/     # 控制器层
│   ├── models/          # 数据模型
│   ├── routes/          # 路由配置
│   ├── views/           # 前端页面
│   ├── public/          # 静态资源
│   └── config/          # 配置文件
├── docs/                # 项目文档
├── tests/               # 测试用例
└── README.md
```

## 安装部署

### 环境要求
- Node.js 16.+
- MySQL 8.+
- Git

### 快速开始

1. **克隆项目**
```bash
git clone https://github.com/volt1m/myproject_61.git
cd myproject_61
```

2. **安装依赖**
```bash
npm install
```

3. **数据库配置**
```sql
CREATE DATABASE real_estate_db;
-- 导入 database/schema.sql
```

4. **环境配置**
```bash
cp .env.example .env
# 编辑 .env 文件配置数据库连接
```

5. **启动应用**
```bash
# 开发模式
npm run dev

# 生产模式
npm start
```

6. **访问应用**
```
http://localhost:3000
```

## 使用说明

### 管理员功能
- 添加新房源信息
- 编辑现有房产详情
- 删除下架房源
- 查看数据统计报表

### 用户功能
- 浏览房产列表
- 搜索筛选心仪房源
- 查看房源详细信息
- 收藏关注房源

## 贡献指南

我们欢迎社区贡献！请遵循以下流程：

1. Fork 本项目
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 创建 Pull Request

## 许可证

本项目采用 [MIT License](LICENSE) 开源协议。

## 联系方式

- 项目主页: [GitHub Repository](https://github.com/volt1m/myproject_61)
- 问题反馈: [Issues](https://github.com/volt1m/myproject_61/issues)
- 邮箱: [你的邮箱]

## 更新日志

### v1.0.0 (2024-06-20)
- ✅ 完成基础房产CRUD功能
- ✅ 实现响应式前端界面
- ✅ 添加数据可视化图表
- ✅ 完善项目文档

---

**⭐ 如果这个项目对你有帮助，请给我们一个 Star！**
