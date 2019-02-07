package com.eomcs.lms.listener;

import java.util.HashMap;

public interface ApplicationListener {
  void startApplication(HashMap<String, Object> context);
  void endApplication(HashMap<String, Object> context);
}
