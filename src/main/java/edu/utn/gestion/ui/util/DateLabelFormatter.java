package edu.utn.gestion.ui.util;

import edu.utn.gestion.ui.constants.UIConstants;

import javax.swing.JFormattedTextField;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ASUS on 22/02/2016.
 */
public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(UIConstants.DATE_FORMAT);

    @Override
    public Object stringToValue(String text) throws ParseException, ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return UIConstants.EMPTY_STRING;
    }
}
