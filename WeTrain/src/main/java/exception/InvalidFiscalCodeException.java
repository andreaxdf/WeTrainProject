package exception;

import viewone.AlertFactory;

public class InvalidFiscalCodeException extends InvalidDataException{

    @Override public void alert() {
        AlertFactory.newWarningAlert("OOPS, SOMETHING WENT WRONG!",
                "Fiscal code not valid",
                "Be sure to fill all fields correctly, thanks for your collaboration!");
    }
}
