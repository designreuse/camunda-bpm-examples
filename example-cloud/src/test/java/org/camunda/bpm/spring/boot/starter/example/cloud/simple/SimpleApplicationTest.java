package org.camunda.bpm.spring.boot.starter.example.cloud.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SimpleApplication.class })
public class SimpleApplicationTest {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private SimpleApplication application;

  @Test
  @Timed(millis = 10 * 1000)
  public void would_fail_if_process_not_completed_after_10_seconds() {
    while (!application.contextClosed && !application.isProcessInstanceFinished()) {
      try {
        Thread.sleep(500L);
      } catch (InterruptedException e) {
        logger.debug("interrupted, that is fine: {}", e.getMessage());
      }
    }
  }
}
