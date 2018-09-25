@REM
@ECHO OFF

SET BUILD=%~dp0
SET BUILD_ENV=%BUILD%env
SET ENV_TEMP=%BUILD_ENV%\temp

ECHO 搜索unzip...
FOR /D %%G IN (%BUILD_ENV%\unzip-*) DO SET UNZIP_EXE=%%G\unzip.exe
ECHO %UNZIP_EXE%

ECHO 安装wget...
%UNZIP_EXE% %BUILD_ENV%\wget-*.zip -d %ENV_TEMP%

FOR /D %%G IN (%ENV_TEMP%\wget-*) DO SET WGET_EXE=%%G\wget.exe
ECHO %WGET_EXE%

ECHO 下载Groovy运行时...
SET GROOVY_ZIP=%ENV_TEMP%\groovy.zip
%WGET_EXE% http://dl.bintray.com/groovy/maven/apache-groovy-binary-2.4.15.zip -O %GROOVY_ZIP%

ECHO 安装Groovy运行时...
%UNZIP_EXE% %GROOVY_ZIP% -d %ENV_TEMP%

FOR /D %%G IN (%ENV_TEMP%\groovy-*) DO SET GROOVY_EXE=%%G\bin\groovy.bat
ECHO %GROOVY_EXE%

ECHO 运行构建脚本...
CALL %GROOVY_EXE% %BUILD%..\src\main\groovy\Main.groovy

PAUSE
