package com.dev.spring.batch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBatchTest
@SpringBootTest
public class JobLauncherTest {
	@Autowired
	private BatchJobLauncher batchJobLauncher;

	@Test
	public void testLaunchJobWithValidFileName() {
		final String fileName = "customer_profile_data_20230914.dat";
		final String result = batchJobLauncher.launchJob(fileName);
		assertNotEquals("Job failed: Invalid file name", result);
		assertEquals("COMPLETED", result);
	}

	@Test
	public void testLaunchJobWithInvalidFileName() {
		final String invalidFileName = "invalid_data_20230914.dat";
		final String result = batchJobLauncher.launchJob(invalidFileName);
		assertEquals("Job failed: Invalid file name", result);
	}

	@Test
	public void testLaunchJobWithMissingDateInFileName() {
		final String invalidFileName = "customer_profile_data_.dat";
		final String result = batchJobLauncher.launchJob(invalidFileName);
		assertEquals("Job failed: Invalid file name", result);
	}
}