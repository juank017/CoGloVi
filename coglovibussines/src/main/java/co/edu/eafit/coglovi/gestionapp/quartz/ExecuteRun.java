package co.edu.eafit.coglovi.gestionapp.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ExecuteRun extends QuartzJobBean implements StatefulJob {

	private ExecuteQuartz execute;

	public void setExecute(ExecuteQuartz executeQuartz) {
		execute = executeQuartz;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		execute.run();
	}

}
