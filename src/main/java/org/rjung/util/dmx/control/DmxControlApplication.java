package org.rjung.util.dmx.control;

import org.rjung.util.dmx.control.service.DmxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DmxControlApplication implements ApplicationRunner {
	private final static Logger LOG = LoggerFactory.getLogger(DmxControlApplication.class);

	@Autowired
	private DmxService dmxService;

	public static void main(String[] args) {
		SpringApplication.run(DmxControlApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if(!args.containsOption("configuration") || args.getOptionValues("configureaion").isEmpty()) {
			usage();
		}
		dmxService.start();
		while (true) {
			dmxService.setProgram(3);
			Thread.sleep(15000);
			dmxService.setProgram(2);
			Thread.sleep(15000);
			dmxService.setProgram(1);
			Thread.sleep(15000);
		}
	}

	private void usage() {
		LOG.info("usage:");
		LOG.info("  DmxControlApplication [oprions]");
		LOG.info("");
		LOG.info("available options:");
		LOG.info("  --configuration <file>    Configuration file to load");
	}
}
