package exceptions;

@SuppressWarnings("serial")
public class ObjetoJaExistenteException extends Exception {

	public ObjetoJaExistenteException(String erro) {
		super("ExcecaoDados: " + erro);
	}
}

/*--- Formatted in Sun Java Convention Style on Mon, Oct 30, '00 ---*/

/*------ Formatted by Jindent 3.23 Gold 1.02 Trial --- http://www.jindent.de ------*/
