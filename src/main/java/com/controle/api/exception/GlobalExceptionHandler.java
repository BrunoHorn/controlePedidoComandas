package com.controle.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.controle.api.dto.ExceptionDto;

@ControllerAdvice
public class GlobalExceptionHandler {
	   
	   @ExceptionHandler(EntidadeEmUsoException.class)
	    public ResponseEntity<ExceptionDto> EntidadeEmUsoException(EntidadeEmUsoException e){

	        return ResponseEntity.status(HttpStatus.CONFLICT).body(
	            new ExceptionDto(HttpStatus.CONFLICT, e.getMessage())
	        );
	    }
	   
	   @ExceptionHandler(NegocioException.class)
	    public ResponseEntity<ExceptionDto> NegocioException(NegocioException e){

	        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
	            new ExceptionDto(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage())
	        );
	    }
	   	   
	   @ExceptionHandler(EntidadeNaoEncontradaException.class)
	    public ResponseEntity<ExceptionDto> EntidadeNaoEncontradaException(EntidadeNaoEncontradaException e){

	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
	            new ExceptionDto(HttpStatus.NOT_FOUND, e.getMessage())
	        );
	    }
}