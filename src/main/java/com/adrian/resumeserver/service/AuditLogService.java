package com.adrian.resumeserver.service;

import com.adrian.resumeserver.model.AuditLog;
import com.adrian.resumeserver.repository.AuditLogRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public void log(String action, String entityType, Long entityId) {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        AuditLog entry = new AuditLog();
        entry.setUserEmail(email);
        entry.setAction(action);
        entry.setEntityType(entityType);
        entry.setEntityId(entityId);

        auditLogRepository.save(entry);
    }
}