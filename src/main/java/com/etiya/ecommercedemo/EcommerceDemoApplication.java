package com.etiya.ecommercedemo;

import com.etiya.ecommercedemo.core.util.exceptions.BusinessException;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@SpringBootApplication
@RestControllerAdvice
public class EcommerceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceDemoApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}

	@Bean
	public ResourceBundleMessageSource bundleMessageSource(){
		// Veritabanı bağlantısı..
		// Dosyadan çekme işlemi..
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		// Konfigurasyonlar
		// Veritabanına git
		// Select * from Languages Where code='tr' and key='greetings'
		// isteğinden dönen cevap senin çevirindir.
		messageSource.setBasename("messages");
		//
		return messageSource;
	}

	// Kullanıcıdan dil tercihini header alanından al..
	// Accept-Language
	@Bean
	public LocaleResolver localeResolver(){
		//Session,Cookie
		// Header => Her istekte headerda bir değer varsa bunu baz al.
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		// Accept-Language alanı boş ise default olarak 'US' alanı olarak değerlendir.
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	// Global Exception Handling-Handler
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Object handleValidationException(MethodArgumentNotValidException exception){
		//TODO: ErrorDataResult olarak ilgili hataları döndür.
		Map<String,String> errors = new HashMap<>();

		for(FieldError fieldError : exception.getBindingResult().getFieldErrors()){
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}

		return errors;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String handleBusinessException(BusinessException exception){
		// Result sistemi kullanılacak.
		return exception.getMessage();
	}

	// fonksiyon oluştur => @ExceptionHandler ile handler olduğunu söyle => Parametre olarak yakalayacağı exception türünü ver.
}
