package tacos.service.implement;


import tacos.Taco;
import tacos.data.TacoRepository;
import tacos.service.ITacoRepository;

public class TacoRepositoryImpl implements ITacoRepository{
	private TacoRepository tacoRepository;
	
	public TacoRepositoryImpl(TacoRepository tacoRepository) {
		this.tacoRepository = tacoRepository;
	}
	
	@Override
	public boolean saveTaco(Taco taco) {
		tacoRepository.save(taco);
		return true;
	}

}
