@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  springboot-webmagic-crawler startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and SPRINGBOOT_WEBMAGIC_CRAWLER_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\wtr-etl-crawler-0.1.0.jar;%APP_HOME%\lib\webmagic-extension-0.7.3.jar;%APP_HOME%\lib\webmagic-core-0.7.3.jar;%APP_HOME%\lib\xsoup-0.3.1.jar;%APP_HOME%\lib\jsoup-1.11.3.jar;%APP_HOME%\lib\spring-boot-starter-data-jpa-2.0.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-jdbc-2.0.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-devtools-2.0.1.RELEASE.jar;%APP_HOME%\lib\h2-1.4.197.jar;%APP_HOME%\lib\solr-core-7.3.0.jar;%APP_HOME%\lib\spring-data-solr-3.0.6.RELEASE.jar;%APP_HOME%\lib\groovy-all-2.4.12.jar;%APP_HOME%\lib\unirest-java-1.4.9.jar;%APP_HOME%\lib\solr-solrj-6.6.3.jar;%APP_HOME%\lib\httpmime-4.5.5.jar;%APP_HOME%\lib\httpasyncclient-4.1.3.jar;%APP_HOME%\lib\httpclient-4.5.5.jar;%APP_HOME%\lib\commons-lang3-3.7.jar;%APP_HOME%\lib\HikariCP-2.7.8.jar;%APP_HOME%\lib\spring-data-jpa-2.0.6.RELEASE.jar;%APP_HOME%\lib\spring-data-commons-2.0.6.RELEASE.jar;%APP_HOME%\lib\slf4j-log4j12-1.7.25.jar;%APP_HOME%\lib\json-path-2.4.0.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\commons-collections-3.2.2.jar;%APP_HOME%\lib\fastjson-1.2.28.jar;%APP_HOME%\lib\jedis-2.9.0.jar;%APP_HOME%\lib\spring-boot-starter-aop-2.0.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-2.0.1.RELEASE.jar;%APP_HOME%\lib\spring-orm-5.0.5.RELEASE.jar;%APP_HOME%\lib\spring-jdbc-5.0.5.RELEASE.jar;%APP_HOME%\lib\spring-boot-autoconfigure-2.0.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-2.0.1.RELEASE.jar;%APP_HOME%\lib\hibernate-core-5.2.16.Final.jar;%APP_HOME%\lib\javax.transaction-api-1.2.jar;%APP_HOME%\lib\spring-aspects-5.0.5.RELEASE.jar;%APP_HOME%\lib\lucene-analyzers-common-7.3.0.jar;%APP_HOME%\lib\lucene-analyzers-kuromoji-7.3.0.jar;%APP_HOME%\lib\lucene-analyzers-phonetic-7.3.0.jar;%APP_HOME%\lib\lucene-backward-codecs-7.3.0.jar;%APP_HOME%\lib\lucene-classification-7.3.0.jar;%APP_HOME%\lib\lucene-codecs-7.3.0.jar;%APP_HOME%\lib\lucene-core-7.3.0.jar;%APP_HOME%\lib\lucene-expressions-7.3.0.jar;%APP_HOME%\lib\lucene-grouping-7.3.0.jar;%APP_HOME%\lib\lucene-highlighter-7.3.0.jar;%APP_HOME%\lib\lucene-join-7.3.0.jar;%APP_HOME%\lib\lucene-memory-7.3.0.jar;%APP_HOME%\lib\lucene-misc-7.3.0.jar;%APP_HOME%\lib\lucene-queries-7.3.0.jar;%APP_HOME%\lib\lucene-queryparser-7.3.0.jar;%APP_HOME%\lib\lucene-sandbox-7.3.0.jar;%APP_HOME%\lib\lucene-spatial-extras-7.3.0.jar;%APP_HOME%\lib\lucene-spatial3d-7.3.0.jar;%APP_HOME%\lib\lucene-suggest-7.3.0.jar;%APP_HOME%\lib\hppc-0.7.3.jar;%APP_HOME%\lib\jackson-databind-2.9.5.jar;%APP_HOME%\lib\jackson-annotations-2.9.0.jar;%APP_HOME%\lib\jackson-core-2.9.5.jar;%APP_HOME%\lib\jackson-dataformat-smile-2.9.5.jar;%APP_HOME%\lib\caffeine-2.6.2.jar;%APP_HOME%\lib\guava-14.0.1.jar;%APP_HOME%\lib\protobuf-java-3.1.0.jar;%APP_HOME%\lib\t-digest-3.1.jar;%APP_HOME%\lib\commons-cli-1.2.jar;%APP_HOME%\lib\commons-codec-1.11.jar;%APP_HOME%\lib\commons-configuration-1.6.jar;%APP_HOME%\lib\commons-fileupload-1.3.2.jar;%APP_HOME%\lib\commons-io-2.5.jar;%APP_HOME%\lib\commons-lang-2.6.jar;%APP_HOME%\lib\dom4j-1.6.1.jar;%APP_HOME%\lib\gmetric4j-1.0.7.jar;%APP_HOME%\lib\metrics-core-3.2.6.jar;%APP_HOME%\lib\metrics-ganglia-3.2.6.jar;%APP_HOME%\lib\metrics-graphite-3.2.6.jar;%APP_HOME%\lib\metrics-jetty9-3.2.6.jar;%APP_HOME%\lib\metrics-jvm-3.2.6.jar;%APP_HOME%\lib\javax.servlet-api-3.1.0.jar;%APP_HOME%\lib\joda-time-2.9.9.jar;%APP_HOME%\lib\log4j-1.2.17.jar;%APP_HOME%\lib\eigenbase-properties-1.1.5.jar;%APP_HOME%\lib\antlr4-runtime-4.5.1-1.jar;%APP_HOME%\lib\calcite-core-1.13.0.jar;%APP_HOME%\lib\calcite-linq4j-1.13.0.jar;%APP_HOME%\lib\avatica-core-1.10.0.jar;%APP_HOME%\lib\commons-exec-1.3.jar;%APP_HOME%\lib\curator-client-2.8.0.jar;%APP_HOME%\lib\curator-framework-2.8.0.jar;%APP_HOME%\lib\curator-recipes-2.8.0.jar;%APP_HOME%\lib\hadoop-annotations-2.7.4.jar;%APP_HOME%\lib\hadoop-auth-2.7.4.jar;%APP_HOME%\lib\hadoop-common-2.7.4.jar;%APP_HOME%\lib\hadoop-hdfs-2.7.4.jar;%APP_HOME%\lib\htrace-core-3.2.0-incubating.jar;%APP_HOME%\lib\httpcore-nio-4.4.9.jar;%APP_HOME%\lib\httpcore-4.4.9.jar;%APP_HOME%\lib\zookeeper-3.4.11.jar;%APP_HOME%\lib\jackson-core-asl-1.9.13.jar;%APP_HOME%\lib\jackson-mapper-asl-1.9.13.jar;%APP_HOME%\lib\commons-compiler-2.7.6.jar;%APP_HOME%\lib\janino-3.0.8.jar;%APP_HOME%\lib\stax2-api-3.1.4.jar;%APP_HOME%\lib\woodstox-core-asl-4.4.1.jar;%APP_HOME%\lib\jetty-continuation-9.4.9.v20180320.jar;%APP_HOME%\lib\jetty-deploy-9.4.9.v20180320.jar;%APP_HOME%\lib\jetty-http-9.4.9.v20180320.jar;%APP_HOME%\lib\jetty-io-9.4.9.v20180320.jar;%APP_HOME%\lib\jetty-jmx-9.4.9.v20180320.jar;%APP_HOME%\lib\jetty-rewrite-9.4.9.v20180320.jar;%APP_HOME%\lib\jetty-security-9.4.9.v20180320.jar;%APP_HOME%\lib\jetty-server-9.4.9.v20180320.jar;%APP_HOME%\lib\jetty-servlet-9.4.9.v20180320.jar;%APP_HOME%\lib\jetty-servlets-9.4.9.v20180320.jar;%APP_HOME%\lib\jetty-util-9.4.9.v20180320.jar;%APP_HOME%\lib\jetty-webapp-9.4.9.v20180320.jar;%APP_HOME%\lib\jetty-xml-9.4.9.v20180320.jar;%APP_HOME%\lib\spatial4j-0.7.jar;%APP_HOME%\lib\noggit-0.8.jar;%APP_HOME%\lib\json-smart-2.3.jar;%APP_HOME%\lib\accessors-smart-1.2.jar;%APP_HOME%\lib\asm-5.1.jar;%APP_HOME%\lib\asm-commons-5.1.jar;%APP_HOME%\lib\org.restlet-2.3.0.jar;%APP_HOME%\lib\org.restlet.ext.servlet-2.3.0.jar;%APP_HOME%\lib\jcl-over-slf4j-1.7.25.jar;%APP_HOME%\lib\spring-context-5.0.5.RELEASE.jar;%APP_HOME%\lib\spring-tx-5.0.5.RELEASE.jar;%APP_HOME%\lib\json-20160212.jar;%APP_HOME%\lib\assertj-core-3.9.1.jar;%APP_HOME%\lib\commons-pool2-2.5.0.jar;%APP_HOME%\lib\javax.annotation-api-1.3.2.jar;%APP_HOME%\lib\spring-aop-5.0.5.RELEASE.jar;%APP_HOME%\lib\spring-beans-5.0.5.RELEASE.jar;%APP_HOME%\lib\spring-expression-5.0.5.RELEASE.jar;%APP_HOME%\lib\spring-core-5.0.5.RELEASE.jar;%APP_HOME%\lib\snakeyaml-1.19.jar;%APP_HOME%\lib\aspectjweaver-1.8.13.jar;%APP_HOME%\lib\hibernate-commons-annotations-5.0.1.Final.jar;%APP_HOME%\lib\jboss-logging-3.3.2.Final.jar;%APP_HOME%\lib\hibernate-jpa-2.1-api-1.0.0.Final.jar;%APP_HOME%\lib\javassist-3.22.0-GA.jar;%APP_HOME%\lib\antlr-2.7.7.jar;%APP_HOME%\lib\jandex-2.0.3.Final.jar;%APP_HOME%\lib\classmate-1.3.4.jar;%APP_HOME%\lib\spring-jcl-5.0.5.RELEASE.jar

@rem Execute springboot-webmagic-crawler
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %SPRINGBOOT_WEBMAGIC_CRAWLER_OPTS%  -classpath "%CLASSPATH%" zipi.WaitroseRecipe %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable SPRINGBOOT_WEBMAGIC_CRAWLER_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%SPRINGBOOT_WEBMAGIC_CRAWLER_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
