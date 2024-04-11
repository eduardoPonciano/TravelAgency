package br.com.eduardo.ponciano.travel.commonsc.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.eduardo.ponciano.travel.commons.i18n.CustomMessageSourceI18n;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Component
public class GlobalExceptionHandler {

	@Autowired
	private CustomMessageSourceI18n msg;
	
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	@ExceptionHandler({ ResponseStatusException.class })
    public Error handleConflict(ResponseStatusException ex) {
		//TODO estudar viabilidade de unificar as exceções utilizando o status HTTP [HttpStatus satushttp = ex.getStatus()]
		Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), "Erro no envio");
		return error;
    }
		
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Error methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<org.springframework.validation.FieldError> fieldErrors = result.getFieldErrors();
		
		return processFieldErrors(fieldErrors);
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(MissingPathVariableException.class)
	public Error methodMissingPathVariableException(MissingPathVariableException ex) {
		Error error = new Error(BAD_REQUEST.value(), BAD_REQUEST.name(), "Erro: : ["+ex.getMessage()+"]");
		return error;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(MissingRequestCookieException.class)
	public Error methodMissingRequestCookieException(MissingRequestCookieException ex) {
		Error error = new Error(BAD_REQUEST.value(), BAD_REQUEST.name(), "Erro: : ["+ex.getMessage()+"]");
		return error;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(MissingRequestHeaderException.class)
	public Error methodMissingRequestHeaderException(MissingRequestHeaderException ex) {
		Error error = new Error(BAD_REQUEST.value(), BAD_REQUEST.name(), "Erro: : ["+ex.getMessage()+"]");
		return error;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(UnsatisfiedServletRequestParameterException.class)
	public Error methodUnsatisfiedServletRequestParameterException(UnsatisfiedServletRequestParameterException ex) {
		Error error = new Error(BAD_REQUEST.value(), BAD_REQUEST.name(), "Erro: : ["+ex.getMessage()+"]");
		return error;
	}
	
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	@ExceptionHandler(Exception.class)
	public Error methodBadRequestException(Exception ex) {
		Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), "Erro: ["+ex.getMessage()+"]");
		return error;
	}
	
	private Error processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
		Error error = new Error(BAD_REQUEST.value(), BAD_REQUEST.name(), "Erro ao realizar a validação");
		
		List<Error.Errors> erros =  new ArrayList<Error.Errors>();
		for (org.springframework.validation.FieldError fieldError : fieldErrors) {
			if("".equals(fieldError.getDefaultMessage()))
				continue;

			String status = msg.getMessage(fieldError.getDefaultMessage()+".codigo");
			String message = msg.getMessage(fieldError.getDefaultMessage());
			if(message.contains("{0}")) {
				message = msg.getMessageWhithParam(fieldError.getDefaultMessage(),fieldError.getField());
			}
			Error.Errors errors = new Error.Errors(status, message, fieldError.getObjectName(), fieldError.getField(), fieldError.getCode());
			erros.add(errors);
		}
		
		error.setErrors(erros);
		return error;
	}
	
	public static class Error {
		
		private int status;
		private String error;
		private String message;
		
		@JsonInclude(Include.NON_NULL)
		private List<Errors> errors;

		public Error(int status, String error, String message) {
			super();
			this.status = status;
			this.error = error;
			this.message = message;
		}

		public int getStatus() {
			return status;
		}

		public String getError() {
			return error;
		}

		public String getMessage() {
			return message;
		}

		public List<Errors> getErrors() {
			return errors;
		}

		public void setErrors(List<Errors> errors) {
			this.errors = errors;
		}

		static class Errors {
			private int status;
			private String message;
			@JsonInclude(Include.NON_NULL)
			private String objectName;
			@JsonInclude(Include.NON_NULL)
			private String field;
			@JsonInclude(Include.NON_NULL)
			private String code;

			public Errors(String status, String message, String objectName, String field, String code) {
				super();
				try {
					this.status = Integer.parseInt(status);
					this.message = message;
				} catch(Exception e ) {
					this.message = "Não foi possivel ler a mensagem '" + message + "', favor entrar em contato com o suporte";
				}
				this.objectName = objectName;
				this.field = field;
				this.code = code;
			}
			public Errors(String status, String message) {
				super();
				this.status = Integer.parseInt(status);
				this.message = message;
			}

			public int getStatus() {
				return status;
			}

			public String getMessage() {
				return message;
			}

			public String getObjectName() {
				return objectName;
			}


			public String getField() {
				return field;
			}

			public String getCode() {
				return code;
			}
		}
	}	

}

