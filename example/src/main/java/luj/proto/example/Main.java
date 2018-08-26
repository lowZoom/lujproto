package luj.proto.example;

import luj.proto.example.login.ui.LoginUi;

public final class Main {

  public static void main(String[] args) {
    new Main(LoginUi.create()).start();
  }

  private Main(LoginUi loginUi) {
    _loginUi = loginUi;
  }

  private void start() {
    _loginUi.show();
  }

  private final LoginUi _loginUi;
}
