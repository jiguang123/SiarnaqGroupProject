package levelBuilderControllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import org.json.JSONException;

import sixesWildEntity.Level;
import JSONSerializer.LevelJSONSerializer;
import levelBuilderBoundary.LevelBuilderApplication;
import levelBuilderEntity.LevelBuilder;

public class ChoosingLevelController implements ActionListener{
	
	public static final String TAG = "ChoosingLevelController :: ";
	
	public LevelBuilder model;
	public LevelBuilderApplication app;
	
	public ChoosingLevelController(LevelBuilderApplication app) throws IOException, JSONException{
		this.model = LevelBuilder.newInstance();
		this.app = app;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		JComboBox<String> cb = (JComboBox<String>)event.getSource();
		
		String fileNameToSave = model.getLevelType() + " " + model.getLevelNumber();
		if(fileNameToSave.equals("Puzzle 0")) {
			System.out.println(TAG + "Nothing to Save");
		} else {
			LevelJSONSerializer json = new LevelJSONSerializer(fileNameToSave);
			try {
				json.saveSingleLevel(model.generateLevel(model.getLevelType()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String selected = (String)cb.getSelectedItem();
		if(selected.equals("New Level")) {
			System.out.println(TAG + "New Level Selected");
			//you can do something here if you can
			return;
		}
		LevelJSONSerializer load = new LevelJSONSerializer(selected);
		System.out.println(TAG + "Save:" + fileNameToSave + " Selected:" + selected.substring(0, 1));
		try {
			//Level level = LevelBuilder.getLevel(); 
			LevelBuilder.level = load.loadSingleLevel(selected.substring(0, 1));
			//System.out.println(TAG + "level: " + level.getLevelType() + " " + level.getLevelNumber());
			//assign level's values to all LevelBuilder's attributes
			model.assignEntities(selected.substring(0, selected.indexOf(" ")));
			//change boundary's values
			app.getInputPanel().getLevel().setText(String.valueOf(model.getLevelNumber()));
			app.getInputPanel().getScore1().setText(String.valueOf(model.getStarScore(0)));
			app.getInputPanel().getScore2().setText(String.valueOf(model.getStarScore(1)));
			app.getInputPanel().getScore3().setText(String.valueOf(model.getStarScore(2)));
			app.getInputPanel().getMaxMoves().setText(String.valueOf(model.getMoves()));
			app.getInputPanel().getSeconds().setText(String.valueOf(model.getSeconds()));
			
			app.getInputPanel().getPercent1().setText(String.valueOf(100*model.getPercent(0)));
			app.getInputPanel().getPercent2().setText(String.valueOf(100*model.getPercent(1)));
			app.getInputPanel().getPercent3().setText(String.valueOf(100*model.getPercent(2)));
			app.getInputPanel().getPercent4().setText(String.valueOf(100*model.getPercent(3)));
			app.getInputPanel().getPercent5().setText(String.valueOf(100*model.getPercent(4)));
			app.getInputPanel().getPercent6().setText(String.valueOf(100*model.getPercent(5)));
			
			app.getInputPanel().getSwapEnabled().setSelected(model.getSpecialEnabled(2));
			app.getInputPanel().getResetEnabled().setSelected(model.getSpecialEnabled(1));
			app.getInputPanel().getRemoveEnabled().setSelected(model.getSpecialEnabled(3));
			
			app.getInputPanel().getPercentx1().setText(String.valueOf(100*model.getPercentM(0)));
			app.getInputPanel().getPercentx2().setText(String.valueOf(100*model.getPercentM(1)));
			app.getInputPanel().getPercentx3().setText(String.valueOf(100*model.getPercentM(2)));
			
			app.getInputPanel().getType().setSelectedItem(selected.substring(0, selected.indexOf(" ")));
			System.out.println("!!!: " + selected.substring(0, selected.indexOf(" ")));
			
			//Game Panel
			for(int i=0;i<9;i++){
				for(int j=0;j<9;j++){
					if(!model.getTileActiveAt(i, j)){
						app.getGamePanel().getTile(i, j).setSelected(true);
					}
					else{
						app.getGamePanel().getTile(i, j).setSelected(false);
					}
				}
			}
			
			//empty 2 stacks
			model.moveStack.clear();
			model.redoStack.clear();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}

}