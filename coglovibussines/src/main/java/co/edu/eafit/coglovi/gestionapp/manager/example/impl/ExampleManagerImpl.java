package co.edu.eafit.coglovi.gestionapp.manager.example.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eafit.coglovi.gestionapp.dao.example.ExampleDao;
import co.edu.eafit.coglovi.gestionapp.manager.example.ExampleManager;

@Service
public class ExampleManagerImpl implements ExampleManager{
	
	@Autowired
	private ExampleDao exampleDao;
	
	@Override
	public void selecExample(){
		exampleDao.selectSysdate();
	}
	
	@Override
	@Transactional
	public void save(){
		
	}
}
