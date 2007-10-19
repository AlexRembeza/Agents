package modules;

import general.JavaElementXML;
import helpers.FormDataHelper;

import manager.Pane;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.graphics.* ;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.*;

import sml.Agent;
import sml.Kernel;
import sml.smlAgentEventId;

import java.util.*;
import debugger.MainFrame;
import dialogs.PropertiesDialog;
import dialogs.ReorderButtonsDialog;
import doc.Document;

public class RHSNumberAccumulatorView extends RHSFunTextView
{
	public RHSNumberAccumulatorView()
	{
	}
	
	public String getModuleBaseName() { return "rhs_number_accumulator" ; }
	
	@Override
	protected void updateNow() {
		setTextSafely(Double.toString(totalValue));
	}

	double totalValue = 0;

	@Override
	public String rhsFunctionHandler(int eventID, Object data,
			String agentName, String functionName, String argument) {
		
		if (functionName.equals(rhsFunName)) {
			double value = 0;
			try {
				value = Double.parseDouble(argument);
			} catch (NumberFormatException e) {
				return "Unknown argument to " + rhsFunName;
			}
			
			totalValue += value;
			
			return "Total value changed to: " + totalValue;
		}
		
		return "Unknown rhs function received in window " + getName() + ".";
	}
	
	@Override
	public void onInitSoar() {
		totalValue = 0;
		updateNow();
	}
}