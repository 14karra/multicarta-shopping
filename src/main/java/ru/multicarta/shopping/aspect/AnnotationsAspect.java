package ru.multicarta.shopping.aspect;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.multicarta.shopping.annotation.ValidateRequestBody;
import ru.multicarta.shopping.exception.ApiException;
import ru.multicarta.shopping.service.XmlService;

import java.lang.reflect.Method;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequiredArgsConstructor
@Component
@Aspect
public class AnnotationsAspect {

    private final XmlService xmlService;

    @SneakyThrows
    @Around("args(.., @RequestBody body) && @annotation(ru.multicarta.shopping.annotation.ValidateRequestBody)")
    public Object proceed(final ProceedingJoinPoint call, final Object body) {
        final var request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        if (!request.getMethod().equals(GET.name()) && body != null) {
            Class xmlValidationClass = getAnnotationValue(call);
            xmlService.performValidation(xmlValidationClass, (String) body);
        } else if (request.getMethod().equals(GET.name())) {
            throw new IllegalStateException("Invalid mapping from the server. Please contact the administrator.");
        } else {
            throw new ApiException("105", "Request body is empty");
        }

        return call.proceed();
    }

    private Class getAnnotationValue(final ProceedingJoinPoint call) {
        Method method = ((MethodSignature) call.getSignature()).getMethod();
        ValidateRequestBody annotation = method.getAnnotation(ValidateRequestBody.class);
        return annotation.value();
    }
}
