package luj.proto.maven.plugin.compile.protoc

import groovy.transform.PackageScope
import luj.groovy.AutoCtor

@PackageScope
@AutoCtor
class RemoteZipImpl implements ProtocInstallerImpl.RemoteZip {

  @Override
  ProtocInstallerImpl.LocalZip download() {
    String downloadUrl = "https://github.com/protocolbuffers/protobuf/releases/download/v$_version/protoc-$_version-win32.zip"

    new AntBuilder().with {
      mkdir(dir: _downloadPath)

      String userAgent = 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36'
//      get(src: downloadUrl, dest: _downloadPath, useragent: userAgent, verbose: 'true')
    }

    String zipName = downloadUrl.split('/')[-1]
    return new LocalZipImpl(_downloadPath, zipName)
  }

  private String _version

  private String _downloadPath
}
