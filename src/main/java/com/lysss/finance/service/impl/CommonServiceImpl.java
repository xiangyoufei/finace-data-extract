package com.lysss.finance.service.impl;

import com.lysss.finance.entity.Holiday;
import com.lysss.finance.service.CommonService;
import com.lysss.finance.util.SpringContext;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommonServiceImpl implements CommonService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<LocalDate> getHolidays() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Holiday> criteriaQuery = criteriaBuilder.createQuery(Holiday.class);
        Root<Holiday> root = criteriaQuery.from(Holiday.class);
        criteriaQuery.select(root);
        final TypedQuery<Holiday> queryResult = entityManager.createQuery(criteriaQuery);
        final List<Holiday> result = queryResult.getResultList();
        return result.stream().map(Holiday::getDate).collect(Collectors.toList());
    }


    @Override
    public Object executeByReflect(String beanName, String methodName, Object... params) {
        final Object bean = SpringContext.getBean(beanName);
        try {
            final Method method = bean.getClass().getDeclaredMethod(methodName);
            method.setAccessible(true);
            if (Objects.isNull(params)) {
                return method.invoke(bean);
            } else {
                return method.invoke(bean, params);
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
