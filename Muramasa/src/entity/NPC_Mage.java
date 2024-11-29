package entity;

import main.GamePanel;

public class NPC_Mage extends Entity{
	
	public NPC_Mage(GamePanel gp) {
		
		super(gp);
		this.gp = gp;
		name = "mage";
		eWidth = (int)(gp.tileSize * 1.8);
		eHeight = (int)(gp.tileSize * 1.8);
		direction = "down";
		speed = 0;
		onPath = false;
		dialogueSet = 0;
		dying = false;
		numOfDirecion = 2;
		getImage();
		setDialogue();
		setDefaultSolidArea(0, 0, 0, 0, 0, 0);
	}
	
	public void getImage() {

		left1 = setup("/npc/mage_left_1", eWidth, eHeight);
		left2 = setup("/npc/mage_left_2", eWidth, eHeight);
		right1 = setup("/npc/mage_right_1", eWidth, eHeight);
		right2 = setup("/npc/mage_right_2", eWidth, eHeight);
		up1 = left1;
		up2 = left2;
		down1 = right1;
		down2 = right2;
	}
	
	public void setAction() {
		
		if(onPath == true) {
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
		}
	}
	
	public void setDialogue() {
	
		int i = 0;
//
		dialogues[0][i] = "Yagami: Đây... đây là đâu? Ý thức của mình... Ông là ai?\nSao ta lại ở đây"; i++;
		dialogues[0][i] = "Wizard: Cuối cùng... ta đã thành công. Sau hàng nghìn\nnăm, nhân loại lại có cơ hội cứu rỗi thế giới này."; i++;
		dialogues[0][i] = "Wizard: Ta là phù thủy duy nhất sót lại sau diệt chủng.\nChính ta đã triệu hồi ngươi đến thế giới Muramasa này."; i++;
		dialogues[0][i] = "Yagami: Ta ư? Một kẻ yếu đuối, chẳng khác nào bóng ma\nlạc lõng giữa thế giới con người. Ngươi chọn nhầm rồi,\nhãy đưa ta về ngay lập tức!"; i++;
		dialogues[0][i] = "Wizard: Ngươi đừng lo. Mọi thứ ở đây đều yếu kém hơn thế\ngiới của ngươi. Nhưng thật đáng tiếc, phép triệu hồi này\nchỉ có một chiều."; i++;
		dialogues[0][i] = "Wizard: Nếu muốn trở về, ngươi phải hạ gục Shinigami\nkẻ đã gieo rắc kinh hoàng suốt nghìn năm qua."; i++;
		dialogues[0][i] = "Yagami: Shinigami? Việc đó đâu phải của ta, đó là chuyện\ncủa các ngươi. Hãy tiêu diệt hắn rồi đưa ta về!"; i++;
		dialogues[0][i] = "Wizard: Sức lực chúng ta không còn đủ. Thân xác mà ngươi\nđang nắm giữ là chiến binh mạnh nhất còn lại, ta đã đưa\nlinh hồn ngươi vào đó với hy vọng cứu rỗi thế gian này."; i++;
		dialogues[0][i] = "Wizard: Xin ngươi, hãy giúp chúng ta."; i++;
		dialogues[0][i] = "Yagami: Nếu ta tử trận nơi đây, thì điều gì sẽ xảy ra?"; i++;
		dialogues[0][i] = "Wizard: Linh hồn ngươi sẽ tan biến vào cõi hư vô, thân\nxác ngươi ở thế giới cũ cũng sẽ hóa thành cát bụi,\nvĩnh viễn không thể hồi sinh."; i++;
		dialogues[0][i] = "Yagami: Hừm… Vậy cũng được. Ở thế giới cũ, ta chỉ là một\nkẻ thừa thãi, chẳng ai thèm quan tâm. Đây sẽ là cơ hội\nduy nhất để ta sống như một con người thật sự."; i++;
		dialogues[0][i] = "Wizard: Từ nay, ngươi sẽ không còn mang cái tên Yagami\nRaito nữa, ngươi sẽ là Kira, đấng cứu thế của vùng đất\nnày."; i++;
		
		i = 0;
		dialogues[1][i] = "Kira: Được rồi. Mau dẫn ta đến chỗ hắn, ta muốn giải\nquyết sớm việc này. Khoan đã... ông...trông ông..."; i++;
		dialogues[1][i] = "Wizard: ta đã bán linh hồn mình cho quỷ dữ để có thể\ntriệu hồi ngươi đến đây. Giờ đã đến lúc ta phải rời khỏi\nthế giới này."; i++;
		dialogues[1][i] = "Wizard: Ta để lại con Griffon này, nó sẽ dẫn đường đến\nchỗ tên Ishigami. Mong ngươi thành công...\nVĩnh biệt..."; i++;
//		
		i = 0;
		dialogues[2][i] = "Kira: Lão già, sao lão lại ở đây? Đừng nói với ta\nlão chính là...."; i++;
		dialogues[2][i] = "Wizard: Đúng, ta cũng không ngờ rằng ngươi lại mạnh\nlên trong khoảng thời gian ngắn như vậy."; i++;
		dialogues[2][i] = "Kira: Vậy thì tại sao? Nếu người đang cai trị nơi này\nthì tại sao lại gọi ta tới hả?"; i++;
		dialogues[2][i] = "Wizard: Ngươi có biết không, khi kẻ mù tìm ra ánh sáng\nthì thứ đầu tiên họ vứt đi chính là cây gậy."; i++;
		dialogues[2][i] = "Wizard: Vào thời Trung Cổ, khi nền văn minh nhân loại\ndần phát triển, loài người bắt đầu coi phù thủy bọn ta\nnhư một mối đe dọa cần tận diệt."; i++;
		dialogues[2][i] = "Wizard: Các cuộc thanh trừng, tàn sát đã dấy lên lòng\ncăm thù đối với con người."; i++;
		dialogues[2][i] = "Wizard: Để có được sức mạnh, tuổi thọ này, chính tay ta\nđã phải tước đi sinh mệnh của những người ta yêu\nthương nhất."; i++;
		dialogues[2][i] = "Wizard: Hai người mà ngươi vừa giao chiến chính là vợ\nvà con trai ta. Vì không nỡ nhìn họ biến mất nên ta\nđã để họ sống mãi trong hình hài đó."; i++;
		dialogues[2][i] = "Wizard: Suốt hàng trăm, hàng nghìn năm qua, ta đã đạt\nđược mục đích của mình. Lũ con người đã phải nếm trải\nnhững gì chúng đã làm."; i++;
		dialogues[2][i] = "Wizard: Nhưng kể từ khi thân xác ngươi đang mang bị ta\nđánh bại sự cô độc bắt đầu bủa vây lấy cơ thể của ta."; i++;
		dialogues[2][i] = "Wizard: Ngươi có hiểu cảm giác nhàm chán, cô đơn khi\nbản thân là cá thể duy nhất tồn tại không hả?"; i++;
		dialogues[2][i] = "Wizard: Chính điều đó đã thúc đẩy ta phải tìm ra ai đó\nđủ mạnh để chống lại mình, như vậy chẳng phải thú vị\nhơn nhiều sao?"; i++;
		dialogues[2][i] = "Kira: Hừ, ta không quan tâm lòng căm thù hay sự nhàm\nchán của ngươi như thế nào."; i++;
		dialogues[2][i] = "Kira: Như đã nói ngay từ lúc bắt đầu. Bất kỳ ai ngáng\nđường ta đi đều sẽ phải chết!"; i++;
		dialogues[2][i] = "Wizard: Vậy nên nhiệm vụ của ngươi bây giờ chính là phải\nlấy được mạng của ta. Ta đã chờ ngày này lâu lắm rồi.\nĐã đến ta đoàn tụ với gia đình rồi."; i++;
		dialogues[2][i] = "Wizard: Giết ta xong hãy đi về phía Tây, nơi đó có cánh\ncổng đưa ngươi quay lại thế giới của mình."; i++;
		dialogues[2][i] = "Kira: Ngươi chưa thể chết được đâu, giờ là lúc để ngươi\ntrả giá cho những gì mình đã làm."; i++;
		dialogues[2][i] = "Kira: Hãy quy phụng và làm việc dưới trướng Kira ta,\nđến với thế giới loài người bên kia và cùng ta thanh\nlọc lại toàn bộ."; i++;
		dialogues[2][i] = "Wizard: Cái gì? Ngươi... Ngươi đang toan tính điều gì\nvậy?"; i++;
		dialogues[2][i] = "Kira: Bất kỳ xã hội nào cũng đều có tình trạng như nhau\nnơi mà kẻ mạnh đàn áp kẻ yếu. Ta muốn tạo ra 1 thế giới\nnơi mà mọi thứ đều công bằng với mọi sinh vật sống."; i++;
		dialogues[2][i] = "Kira: Chẳng phải ngươi cũng căm hận con người sao, lão\ngià?"; i++;
		dialogues[2][i] = "Kira: Chúng ta là những con mồi luôn lo sợ bị săn bắt ư?"; i++;
		dialogues[2][i] = "Kira: Không! Chúng ta không phải con mồi, chúng ta là\nthợ săn!"; i++;
		dialogues[2][i] = "Kira: Đến đây! Hãy cho bọn chúng biết thế nào là sức\nmạnh thật sự!."; i++;
		dialogues[2][i] = "Wizard: Ngươi điên thật rồi Kira à! Nhưng mà tiếc thay\nta cũng là một kẻ điên giông như ngươi vậy."; i++;
		dialogues[2][i] = "Wizard: Được thôi! Ta sẽ giao tính mạng này cho ngươi.\nNhưng nếu tương lai ngươi xây dựng khác với những gì đã\nnói. Khi đó, đích thân ta sẽ lấy mạng ngươi một lần nữa."; i++;
		dialogues[2][i] = "Kira: Sao cũng được, ta cũng đâu phải kẻ hai lời. Đi\nthôi lão già!"; i++;
		
	}
	
	public void speak() {
		if(dying == true) {
			dialogueSet = 1;
		}else {
			dialogueSet = 0;
		}
		facePlayer();
		if(gp.currentMap == 0) {
			startDialogue(this, dialogueSet);
		}
		else if(gp.currentMap == 4) {
			startDialogue(this, 2);
			onPath = true;
		}
		
	}
}

