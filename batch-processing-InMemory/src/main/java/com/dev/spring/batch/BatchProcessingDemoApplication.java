package com.dev.spring.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BatchProcessingDemoApplication {

	public static void main(String[] args) {
		// SpringApplication.run(BatchProcessingDemoApplication.class, args);

		// Initialize the Spring Application context
		final ApplicationContext context = SpringApplication.run(BatchProcessingDemoApplication.class, args);

		// Get the BatchJobLauncher bean from the context
		final BatchJobLauncher batchJobLauncher = context.getBean(BatchJobLauncher.class);

		// Call the launchJob() method with a valid file name
		final String fileName = "customer_profile_data_20230914.dat"; // Example file name
		final String result = batchJobLauncher.launchJob(fileName);

		// Print the job result to check if it was successful
		System.out.println("Job Execution Result: " + result);
	}
}
