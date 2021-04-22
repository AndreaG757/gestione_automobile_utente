package it.prova.gestioneautomobileutente.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;


//nel nome della classe vi è Articolo in quanto è una classe specifica
public class UtilityAutomobileForm {

	public static boolean validateInput(String codiceInputParam, String descrizioneInputParam,
			String prezzoInputStringParam, String dataPubblicazioneStringParam) {
		// prima controlliamo che non siano vuoti
		if (StringUtils.isBlank(codiceInputParam) || StringUtils.isBlank(descrizioneInputParam)
				|| !NumberUtils.isCreatable(prezzoInputStringParam) || StringUtils.isBlank(dataPubblicazioneStringParam) || !prezzoInputStringParam.matches("^(?!0+$)\\d+$")) {
			return false;
		}
		return true;
	}

	public static Date parseDataPubblicazioneFromString(String dataPubblicazioneStringParam) {
		if (StringUtils.isBlank(dataPubblicazioneStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataPubblicazioneStringParam);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String avoidNumberException(String cilindrata) {
		if(cilindrata != null)
			try {
				Integer.parseInt(cilindrata);
			} catch (NumberFormatException e) {
				cilindrata = null;
			}		
		
		return cilindrata;
	}

}
