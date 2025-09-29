package com.example.dagger_java.common.dependencyinjection;

import com.example.dagger_java.questions.FetchQuestionDetailsUseCase;
import com.example.dagger_java.questions.FetchQuestionUseCase;
import com.example.dagger_java.screens.common.ScreensNavigator;
import com.example.dagger_java.screens.common.dialogs.DialogsNavigator;
import com.example.dagger_java.screens.common.viewsmvc.ViewMvcFactory;
import com.example.dagger_java.screens.questiondetails.QuestionDetailsFragment;
import com.example.dagger_java.screens.questionslist.QuestionsListFragment;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Injector {

    private final PresentationCompositionRoot compositionRoot;

    public Injector(PresentationCompositionRoot compositionRoot) {
        this.compositionRoot = compositionRoot;
    }

    public void inject(Object client) {
        for (Field field : getAllFields(client)) {
            if (isAnnotatedForInjection(field)) {
                injectField(client, field);
            }
        }
    }

    private Field[] getAllFields(Object client) {
        Class<?> clientClass = client.getClass();
        return clientClass.getDeclaredFields();
    }

    private boolean isAnnotatedForInjection(Field field) {
        Annotation[] fieldAnnotations = field.getAnnotations();
        for (Annotation annotation : fieldAnnotations) {
            if (annotation.annotationType().equals(Service.class)) {
                return true;
            }
        }
        return false;
    }

    private void injectField(Object client, Field field) {
        boolean isAccessibleInitially = field.isAccessible();
        field.setAccessible(true);
        try {
            field.set(client, getServiceForClass(field.getType()));
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to inject field: " + field.getName(), e);
        }
        field.setAccessible(isAccessibleInitially);
    }

    private Object getServiceForClass(Class<?> type) {
        if (type.equals(DialogsNavigator.class)) {
            return compositionRoot.getDialogNavigator();
        } else if (type.equals(ScreensNavigator.class)) {
            return compositionRoot.getScreensNavigator();
        } else if (type.equals(FetchQuestionUseCase.class)) {
            return compositionRoot.getFetchQuestionUseCase();
        } else if (type.equals(FetchQuestionDetailsUseCase.class)) {
            return compositionRoot.getFetchQuestionDetailsUseCase();
        } else if (type.equals(ViewMvcFactory.class)) {
            return compositionRoot.getViewMvcFactory();
        } else {
            throw new RuntimeException("Unsupported service type: " + type);
        }
    }
}
