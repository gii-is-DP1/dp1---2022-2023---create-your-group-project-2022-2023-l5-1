package org.springframework.samples.minesweeper.audit;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.minesweeper.game.Game;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditService {
	@Autowired
	private AuditRepository auditRepository;

	@Transactional
	public List<Audit> findAll() throws DataAccessException {
		return (List<Audit>) auditRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Audit> findAudits(Integer page,Pageable pageable) throws DataAccessException {
		return auditRepository.findAudits(pageable);
	}

	@Transactional(readOnly = true)
	public Optional<Audit> findAuditById(int id) throws DataAccessException {
		return auditRepository.findById(id);
	}
	
	public void save(Audit audit) {
		auditRepository.save(audit);
	}

    public Audit findActiveAuditByUsername(String username) {
        return auditRepository.findActiveAuditByUsername(username);
    }

	public void deleteAudit(Audit audit) {
		auditRepository.delete(audit);
	}
}