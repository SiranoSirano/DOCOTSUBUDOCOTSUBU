package model;

import java.util.List;

import dao.MutterDAO;

public class PostMutterLogic {

	public void execute(Mutter mutter,List<Mutter> mutterList) {
		mutterList.add(0,mutter);
		//		先頭に追加
	}
	public void execute(Mutter mutter) {

		MutterDAO dao = new MutterDAO();
		dao.create(mutter);
	}

}
