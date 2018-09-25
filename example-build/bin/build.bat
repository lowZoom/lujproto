CD %~dp0
CD env

FOR /D %%G IN (unzip-*) DO SET UNZIP_EXE=%%G\unzip.exe
%UNZIP_EXE% wget-*.zip

FOR /D %%G IN (wget-*) DO SET WGET_EXE=%%G\wget.exe
SET GROOVY_ZIP=groovy.zip
@REM %WGET_EXE% http://dl.bintray.com/groovy/maven/apache-groovy-binary-2.4.15.zip -O %GROOVY_ZIP%

%UNZIP_EXE% %GROOVY_ZIP%
FOR /D %%G IN (groovy-*) DO SET GROOVY_EXE=%%G\bin\groovy.bat

SET GROOVY_EXE=%CD%\%GROOVY_EXE%
CD %~dp0
CALL %GROOVY_EXE% ..\src\main\groovy\Main.groovy

PAUSE
