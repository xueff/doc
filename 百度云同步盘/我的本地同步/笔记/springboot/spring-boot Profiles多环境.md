## Profiles

Spring Profiles提供了一种隔离应用程序配置部分并使其仅在特定环境中可用的方法。任何`@Component`或`@Configuration` 可以标记`@Profile`以限制何时加载，如以下示例所示：

```
@Configuration 
@Profile（“production”）
公共 类 ProductionConfiguration { // ... 
}

	
```

您可以使用`spring.profiles.active` `Environment`属性指定哪些配置文件处于活动状态。您可以使用本章前面介绍的任何方法指定属性。例如，您可以将其包含在您的中`application.properties`，如以下示例所示：

```
spring.profiles.active = dev，hsqldb
```

您还可以使用以下开关在命令行上指定它： `--spring.profiles.active=dev,hsqldb`。