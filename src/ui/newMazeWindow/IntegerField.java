package ui.newMazeWindow;

import javax.swing.*;
import javax.swing.text.*;

public final class IntegerField extends JTextField {

    public IntegerField(int defaultValue) {
        super(String.valueOf(defaultValue));
        PlainDocument doc = (PlainDocument) this.getDocument();
        doc.setDocumentFilter(new StrictlyPositiveIntFilter());
    }

    public int getValue() {
        //In theory, this should never throw an error because we only accepted
        //the inputs that could be interpreted as strictly positive integers.
        return Integer.decode(this.getText());
    }

    //Solution proposed by: https://stackoverflow.com/a/11093360
    //The use of a filter over a key action listener allows preventing copy-pasting characters that are not allowed.
    private static final class StrictlyPositiveIntFilter extends DocumentFilter {

        @Override
        public void insertString(FilterBypass fb, int offset, String string,
                                 AttributeSet attr) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.insert(offset, string);

            if (isStrictlyPositiveInteger(sb.toString())) {
                super.insertString(fb, offset, string, attr);
            }
        }

        private boolean isStrictlyPositiveInteger(String text) {
            try {
                int value = Integer.parseInt(text);
                return value > 0;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text,
                            AttributeSet attrs) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.replace(offset, offset + length, text);

            if (isStrictlyPositiveInteger(sb.toString())) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length)
                throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.delete(offset, offset + length);

            if (isStrictlyPositiveInteger(sb.toString())) {
                super.remove(fb, offset, length);
            }
        }
    }
}
