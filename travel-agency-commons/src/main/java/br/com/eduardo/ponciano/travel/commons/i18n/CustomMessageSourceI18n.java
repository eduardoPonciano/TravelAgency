package br.com.eduardo.ponciano.travel.commons.i18n;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class CustomMessageSourceI18n {

	private static MessageSource msgStatic;

	@Autowired
	private MessageSource msg;

	@PostConstruct
	private void init() {
		msgStatic = this.msg;
	}

	public String getMessage(String code) {
		return msg.getMessage(code, null, Locale.getDefault());
	}

	public static String getMessageStatic(String code) {
		return msgStatic.getMessage(code, null, Locale.getDefault());
	}

	public String getMessageWhithParam(String code, Object... parametros) {
		return MessageFormat.format(getMessage(code), parametros);
	}
}
