# rox-jee-itf-maven-plugin

> Maven plugin to run the Java EE integration tests based on [Java EE Integration Test Framework](https://github.com/lotaris/jee-itf).

## Usage

1. Example of usage in a pom.xml

```xml
<plugin>
	<groupId>com.lotaris.maven.rox.plugins</groupId>
	<artifactId>rox-itf-maven-plugin</artifactId>
	<version>2.2.1</version>
	<configuration>
		<category>Integration - ITF</category>
		<roxConfig>${project.parent.basedir}/src/main/resources/rox.yml</roxConfig>
		<launchUrl>http://localhost:8080/${lotaris.todolist.server.context.root}-test/itf/start?filters={filters}&amp;category={category}&amp;projectName=${project.version}</launchUrl>
	</configuration>
</plugin>
```

### Requirements

* Java 6+

## Contributing

* [Fork](https://help.github.com/articles/fork-a-repo)
* Create a topic branch - `git checkout -b feature`
* Push to your branch - `git push origin feature`
* Create a [pull request](http://help.github.com/pull-requests/) from your branch

Please add a changelog entry with your name for new features and bug fixes.

## License

**rox-jee-itf-maven-plugin** is licensed under the [MIT License](http://opensource.org/licenses/MIT).
See [LICENSE.txt](LICENSE.txt) for the full text.
