package com.dev.spring.batch;

import java.util.regex.Pattern;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BatchJobLauncher {
	private static final String FILE_NAME_PATTERN = "customer_profile_data_\\d{8}\\.dat";

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	public String launchJob(final String fileName) {

		// Adding Job Parameters
		final JobParameters jobParameters = new JobParametersBuilder().addString("fileName", fileName)
				.addLong("startAt", System.currentTimeMillis()).toJobParameters();

		if (isValidFileName(fileName)) {
			try {
				// Launch the job
				final JobExecution jobExecution = jobLauncher.run(job, jobParameters);

				// Return job status
				return jobExecution.getStatus().toString();
			} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
					| JobParametersInvalidException e) {
				e.printStackTrace();
				return "Job failed with exception: " + e.getMessage();
			}
		} else {
			// Log error for invalid file name
			System.err.println("File name pattern mismatch: " + fileName);
			return "Job failed: Invalid file name";
		}
	}

	// Method to validate the file name pattern
	private boolean isValidFileName(String fileName) {
		// Check if the file name matches the pattern
		return Pattern.matches(FILE_NAME_PATTERN, fileName);
	}
}
