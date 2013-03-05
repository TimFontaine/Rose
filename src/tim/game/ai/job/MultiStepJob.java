/**
 * 
 */
package tim.game.ai.job;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import tim.data.back.Node;
import tim.data.back.Path;
import tim.data.unit.Unit;
import tim.game.ai.MoveJob;

/**
 * @author tfontaine
 * a gotojob but after every step another job is executed
 *
 */
public class MultiStepJob extends MoveJob {
	
	public static final int EVALUATION_BEFORE_STEP = -1;
	public static final int EVALUATION_WITH_STEP = -1;
	public static final int EVALUATION_AFTER_STEP = 1;
	
	private Job extraJob;
	Point endLocation;
	
	private int evaluation;
	private boolean turnJob;

	/**
	 * 
	 */
	public MultiStepJob(Unit unit, Point endLocation) {
		super(unit);
		this.endLocation = endLocation;
		init();
	}

	/**
	 * 
	 */
	private void init() {
		turnJob = true;//assert that we do first a move
	}

	/* (non-Javadoc)
	 * @see tim.game.ai.job.Job#start()
	 */
	@Override
	public void start() {
		started = true;
		path = back.findShortestPath(unit.getX(), unit.getY(), endLocation.x, endLocation.y);
	}

	/* (non-Javadoc)
	 * @see tim.game.ai.job.Job#doAction()
	 */
	@Override
	public void doAction() {
		if (!started()) {
			start();
			return;
		}
		if (turnJob) {
			doStep();
		} else {
			doExtraJob();
		}
		//turn false == extrajob is done on last position, turn true, only move is done
		if (testOnDestination() && turnJob == false) {
			onDestination();
		}
		
	}
	
	private void doStep() {
		move();
		turnJob = false;
	}
	
	private void doExtraJob() {
		extraJob.doAction();
		if (extraJob.isFinished()) {
			doStep();
		}
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	public Job getExtraJob() {
		return extraJob;
	}

	public void setExtraJob(Job extraJob) {
		this.extraJob = extraJob;
	}

}
