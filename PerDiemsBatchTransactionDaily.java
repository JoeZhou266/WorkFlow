package com.ish.perDiemsBatch;

import java.util.Date;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.batch.test.JobLauncherTestUtils;



public class PerDiemsBatchTransactionDaily 
{
    public static void main( String[] args )
    {
    	ApplicationContext context = new ClassPathXmlApplicationContext("job/job-transaction-accumulated-jdbc.xml");
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
//		JobRepository jobRepository = (JobRepository) context.getBean("jobRepository");
		Job job = (Job) context.getBean("job_accumulateTransaction");		
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder().addDate("date", new Date()).addString("id_begin", "1").addString(
				"id_end", "999999999");		
		try {			

			JobExecution result = jobLauncher.run(job,
					jobParametersBuilder.toJobParameters());

			ExitStatus es = result.getExitStatus();
			if (es.getExitCode().equals(ExitStatus.COMPLETED.getExitCode())) {
				System.out.println("Job succeeded.");
			} else {
				System.out.println("Joe faild, exitCode=" + es.getExitCode());
			}

		}catch (Exception e) { 
			
			e.printStackTrace();
		}
    }
}


