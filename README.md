# rox-jee-itf-maven-plugin

> Maven plugin to run the Java EE integration tests based on [Java EE Integration Test Framework](https://github.com/lotaris/jee-itf).

## Usage

1. Clone the [rox-commons-maven-plugin](https://github.com/lotaris/rox-commons-maven-plugin)

2. Checkout the tag `v2.2.0` and follow the `README`.

3. Clone this repository.

4. Run the following command

```bash
cd <projectFolder>
mvn clean install
```

5. Put the following dependency in your pom.xml

```xml
<dependency>
  <groupId>com.lotaris.minirox.client</groupId>
  <artifactId>rox-jee-itf-maven-plugin</artifactId>
  <version>2.2.0</version>
</dependenc>
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
