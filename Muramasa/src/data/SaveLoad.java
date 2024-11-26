package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Entity;
import main.GamePanel;

public class SaveLoad {
    GamePanel gp;
    DatabaseConnection dbManager;

    public SaveLoad(GamePanel gp) {
        this.gp = gp;
        this.dbManager = new DatabaseConnection();
    }

    public void save() {
        Connection conn = null;
        try {
            // Lấy kết nối từ dbManager
            conn = dbManager.getConnection();
            conn.setAutoCommit(false); // Bắt đầu giao dịch

            // Kiểm tra xem player có tồn tại trong bảng player với idaccount không
            String playerSql;
            boolean checkPlayerExists = checkPlayerExists(gp.player.playerId);
            if (checkPlayerExists == false) {
                // Câu lệnh INSERT
                playerSql = "INSERT INTO player (id, level, max_life, life, max_mana, mana, strength, exp, next_level_exp, coin, currentArmor, currentWeapon, currentBook, map_id, currentArea, worldX, worldY, skill1, skill2, skill3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement playerStmt = conn.prepareStatement(playerSql, Statement.RETURN_GENERATED_KEYS)) {
                    // Gán tham số
                    playerStmt.setInt(1, gp.player.playerId); // Gán idaccount thay vì playerId
                    playerStmt.setInt(2, gp.player.level);
                    playerStmt.setInt(3, gp.player.maxLife);
                    playerStmt.setInt(4, gp.player.life);
                    playerStmt.setInt(5, gp.player.maxMana);
                    playerStmt.setInt(6, gp.player.mana);
                    playerStmt.setInt(7, gp.player.strength);
                    playerStmt.setInt(8, gp.player.exp);
                    playerStmt.setInt(9, gp.player.nextLevelExp);
                    playerStmt.setInt(10, gp.player.coin);
                    playerStmt.setInt(11, gp.player.getCurrentArmorSlot());
                    playerStmt.setInt(12, gp.player.getCurrentWeaponSlot());
                    playerStmt.setInt(13, gp.player.getCurrentBookSlot());
                    playerStmt.setInt(14, gp.currentMap);
                    playerStmt.setInt(15, gp.currentArea);
                    playerStmt.setInt(16, gp.player.worldX);
                    playerStmt.setInt(17, gp.player.worldY);
                    playerStmt.setBoolean(18, gp.player.canUseSkill1);
                    playerStmt.setBoolean(19, gp.player.canUseSkill2);
                    playerStmt.setBoolean(20, gp.player.canUseSkill3);
                    playerStmt.executeUpdate();
                }
            } else {
                // Câu lệnh UPDATE
                playerSql = "UPDATE player SET level = ?, max_life = ?, life = ?, max_mana = ?, mana = ?, strength = ?, exp = ?, next_level_exp = ?, coin = ?, currentArmor = ?, currentWeapon = ?, currentBook = ?, map_id = ?, currentArea = ?, worldX = ?, worldY = ?, skill1 = ?, skill2 = ?, skill3 = ? WHERE id = ?";
                try (PreparedStatement playerStmt = conn.prepareStatement(playerSql, Statement.RETURN_GENERATED_KEYS)) {
                    
                    playerStmt.setInt(1, gp.player.level);
                    playerStmt.setInt(2, gp.player.maxLife);
                    playerStmt.setInt(3, gp.player.life);
                    playerStmt.setInt(4, gp.player.maxMana);
                    playerStmt.setInt(5, gp.player.mana);
                    playerStmt.setInt(6, gp.player.strength);
                    playerStmt.setInt(7, gp.player.exp);
                    playerStmt.setInt(8, gp.player.nextLevelExp);
                    playerStmt.setInt(9, gp.player.coin);
                    playerStmt.setInt(10, gp.player.getCurrentArmorSlot());
                    playerStmt.setInt(11, gp.player.getCurrentWeaponSlot());
                    playerStmt.setInt(12, gp.player.getCurrentBookSlot());
                    playerStmt.setInt(13, gp.currentMap);
                    playerStmt.setInt(14, gp.currentArea);
                    playerStmt.setInt(15, gp.player.worldX);
                    playerStmt.setInt(16, gp.player.worldY);
                    playerStmt.setBoolean(17, gp.player.canUseSkill1);
                    playerStmt.setBoolean(18, gp.player.canUseSkill2);
                    playerStmt.setBoolean(19, gp.player.canUseSkill3);
                    playerStmt.setInt(20, gp.player.playerId);
                    playerStmt.executeUpdate();
                }
            }
            
            //gamepanel
            String deletePanelSql = "DELETE FROM gamepanel WHERE id_panel = ?";
            try (PreparedStatement deleteStmt = conn.prepareStatement(deletePanelSql)) {
                deleteStmt.setInt(1, gp.player.playerId);
                deleteStmt.executeUpdate();
            }
            
            String panelSql = "INSERT INTO gamepanel (id_panel, dragonBattleOn, defeatDragon, endSummon, endThunderSummon, afterSummon, endDialogue1, endDialogue2, endDialogue3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement panelStmt = conn.prepareStatement(panelSql)) {
                
                panelStmt.setInt(1, gp.player.playerId);
                panelStmt.setBoolean(2, gp.dragonBattleOn);
                panelStmt.setBoolean(3, gp.defeatDragon);
                panelStmt.setBoolean(4, gp.endSummon);
                panelStmt.setBoolean(5, gp.endThunderSummon);
                panelStmt.setBoolean(6, gp.afterSummon);
                panelStmt.setBoolean(7, gp.endDialogue1);
                panelStmt.setBoolean(8, gp.endDialogue2);
                panelStmt.setBoolean(9, gp.endDialogue3);
                panelStmt.executeUpdate();
                
            }
            
            // Xóa dữ liệu cũ của inventory trước khi INSERT mới
            String deleteInventorySql = "DELETE FROM inventory WHERE player_id = ?";
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteInventorySql)) {
                deleteStmt.setInt(1, gp.player.playerId);
                deleteStmt.executeUpdate();
            }

            // Thêm mới inventory
            String inventorySql = "INSERT INTO inventory (player_id, item_name, item_amount) VALUES (?, ?, ?)";
            try (PreparedStatement inventoryStmt = conn.prepareStatement(inventorySql)) {
                for (var item : gp.player.inventory) {
                    inventoryStmt.setInt(1, gp.player.playerId);
                    inventoryStmt.setString(2, item.name);
                    inventoryStmt.setInt(3, item.amount);
                    inventoryStmt.executeUpdate();
                }
            }

         // Xóa dữ liệu cũ của map_objects trước khi INSERT mới
            String deleteMapObjectsSql = "DELETE FROM map_objects WHERE player_id = ?";
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteMapObjectsSql)) {
                deleteStmt.setInt(1, gp.player.playerId);
                deleteStmt.executeUpdate();
            }

            // Đặt lại sequence của objects_id về 1
            String resetObjectIdSql = "ALTER SEQUENCE map_objects_object_id_seq RESTART WITH 1";
            try (Statement resetStmt = conn.createStatement()) {
                resetStmt.executeUpdate(resetObjectIdSql);
            }

            // Thêm mới map objects
            String mapObjectSql = "INSERT INTO map_objects (map_id, object_name, world_x, world_y, loot_name, opened, player_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement mapStmt = conn.prepareStatement(mapObjectSql)) {
                for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                    for (var obj : gp.obj[mapNum]) {
                        if (obj != null) {
                            mapStmt.setInt(1, mapNum);
                            mapStmt.setString(2, obj.name);
                            mapStmt.setInt(3, obj.worldX);
                            mapStmt.setInt(4, obj.worldY);
                            mapStmt.setString(5, obj.loot != null ? obj.loot.name : null);
                            mapStmt.setBoolean(6, obj.opened);
                            mapStmt.setInt(7, gp.player.playerId);
                            mapStmt.executeUpdate();
                        }
                    }
                }
            }

            // Xóa dữ liệu cũ của monster trước khi INSERT mới
            String deleteMonsterSql = "DELETE FROM monster WHERE player_id = ?";
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteMonsterSql)) {
                deleteStmt.setInt(1, gp.player.playerId);
                deleteStmt.executeUpdate();
            }
            
            // Đặt lại sequence của objects_id về 1
            String resetMonsterIdSql = "ALTER SEQUENCE monster_mon_id_seq RESTART WITH 1";
            try (Statement resetStmt = conn.createStatement()) {
                resetStmt.executeUpdate(resetMonsterIdSql);
            }
            
            // Thêm mới map monster
            String monstertSql = "INSERT INTO monster (map_id, mon_name, world_x, world_y, life, alive, player_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement monStmt = conn.prepareStatement(monstertSql)) {
                for (int mapNum = 0; mapNum < gp.maxMap; mapNum++) {
                    for (var mon : gp.monster[mapNum]) {
                        if (mon != null) {
                        	monStmt.setInt(1, mapNum);
                        	monStmt.setString(2, mon.name);
                        	monStmt.setInt(3, mon.worldX);
                        	monStmt.setInt(4, mon.worldY);
                        	monStmt.setInt(5, mon.life);
                        	monStmt.setBoolean(6, mon.alive);
                        	monStmt.setInt(7, gp.player.playerId);
                        	monStmt.executeUpdate();
                        }
                    }
                }
            }

            // Commit giao dịch
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
            // Rollback nếu có lỗi
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            // Đóng kết nối
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

    public void load() {
        try (Connection conn = dbManager.getConnection()) {
        	// Đọc thông tin đồ vật
        	String inventorySql = "SELECT item_name, item_amount FROM inventory WHERE player_id = ?";
        	PreparedStatement inventoryStmt = conn.prepareStatement(inventorySql);
        	inventoryStmt.setInt(1, gp.player.playerId);
        	ResultSet inventoryRs = inventoryStmt.executeQuery();
        	gp.player.inventory.clear();
        	while (inventoryRs.next()) {
        	    Entity item = gp.eGenerator.getObject(inventoryRs.getString("item_name"));
        	    if (item != null) {
        	        item.amount = inventoryRs.getInt("item_amount");
        	        gp.player.inventory.add(item);
        	    }
        	}

        	String playerSql = "SELECT * FROM player WHERE id = ?";
        	PreparedStatement playerStmt = conn.prepareStatement(playerSql);
        	playerStmt.setInt(1, gp.player.playerId); // Use the specific player ID
        	ResultSet playerRs = playerStmt.executeQuery();
        	if (playerRs.next()) {
        	    gp.player.level = playerRs.getInt("level");
        	    gp.player.maxLife = playerRs.getInt("max_life");
        	    gp.player.life = playerRs.getInt("life");
        	    gp.player.maxMana = playerRs.getInt("max_mana");
        	    gp.player.mana = playerRs.getInt("mana");
        	    gp.player.strength = playerRs.getInt("strength");
        	    gp.player.exp = playerRs.getInt("exp");
        	    gp.player.nextLevelExp = playerRs.getInt("next_level_exp");
        	    gp.player.coin = playerRs.getInt("coin");
        	    int armorSlot = playerRs.getInt("currentArmor");
        	    int swordSlot = playerRs.getInt("currentWeapon");
        	    int bookSlot = playerRs.getInt("currentBook");
        	    gp.currentMap = playerRs.getInt("map_id");
        	    gp.nextArea = playerRs.getInt("currentArea");
        	    gp.player.worldX = playerRs.getInt("worldX");
        	    gp.player.worldY = playerRs.getInt("worldY");
        	    gp.player.canUseSkill1 = playerRs.getBoolean("skill1");
        	    gp.player.canUseSkill2 = playerRs.getBoolean("skill2");
        	    gp.player.canUseSkill3 = playerRs.getBoolean("skill3");
        	    
        	    gp.changeTileMap();
        	    gp.setWorld();
        	    
        	    if (armorSlot >= 0 && armorSlot < gp.player.inventory.size()) {
        	        gp.player.currentArmor = gp.player.inventory.get(armorSlot);
        	    }
        	    if (swordSlot >= 0 && swordSlot < gp.player.inventory.size()) {
        	        gp.player.currentWeapon = gp.player.inventory.get(swordSlot);
        	        gp.player.getAttackImage();
        	    }
        	    if (bookSlot >= 0 && bookSlot < gp.player.inventory.size()) {
        	        gp.player.currentBook = gp.player.inventory.get(bookSlot);
        	    }
        	}	

        	//gamePanel
        	String panelSql = "SELECT * FROM gamepanel WHERE id_panel = ?;";
        	PreparedStatement panelStmt = conn.prepareStatement(panelSql);
        	panelStmt.setInt(1, gp.player.playerId);
        	ResultSet panelRs = panelStmt.executeQuery();
        	if(panelRs.next()) {
              
                gp.dragonBattleOn = panelRs.getBoolean("dragonBattleOn");
                gp.defeatDragon = panelRs.getBoolean("defeatDragon");
                gp.endSummon = panelRs.getBoolean("endSummon");
                gp.endThunderSummon = panelRs.getBoolean("endThunderSummon");
                gp.afterSummon = panelRs.getBoolean("afterSummon");
                gp.endDialogue1 = panelRs.getBoolean("endDialogue1");
                gp.endDialogue2 = panelRs.getBoolean("endDialogue2");
                gp.endDialogue3 = panelRs.getBoolean("endDialogue3");
                
        	}
        	
            // Đọc thông tin đối tượng bản đồ
        	String mapObjectSql = "SELECT map_id, object_id, object_name, world_x, world_y, loot_name, opened FROM map_objects WHERE player_id = ?";
        	PreparedStatement mapStmt = conn.prepareStatement(mapObjectSql);
        	mapStmt.setInt(1, gp.player.playerId);
        	ResultSet mapRs = mapStmt.executeQuery();
        	while (mapRs.next()) {
        	    int mapNum = mapRs.getInt("map_id");
        	    int index = mapRs.getInt("object_id") - 1;

        	    if (mapNum < gp.maxMap && index < gp.obj[mapNum].length && !mapRs.getString("object_name").equals("NA")) {
        	        Entity obj = gp.eGenerator.getObject(mapRs.getString("object_name"));
        	        if (obj != null) {
        	            obj.worldX = mapRs.getInt("world_x");
        	            obj.worldY = mapRs.getInt("world_y");
        	            if(obj.name.equals("chest")) {
        	            	obj.loot = gp.eGenerator.getObject(mapRs.getString("loot_name"));
        	            }
        	            obj.setDialogue();
        	            obj.opened = mapRs.getBoolean("opened");
        	            gp.obj[mapNum][index] = obj;
        	            if(obj.opened == true) {
        	            	gp.obj[mapNum][index].down1 = gp.obj[mapNum][index].image2;
        	            }
        	        }
        	    }
        	}
        	
        	//doc thong tin monster
        	String monsterSql = "SELECT map_id, mon_id, mon_name, world_x, world_y, life, alive FROM monster WHERE player_id = ?";
        	PreparedStatement monStmt = conn.prepareStatement(monsterSql);
        	monStmt.setInt(1, gp.player.playerId);
        	ResultSet monRs = monStmt.executeQuery();
        	while (monRs.next()) {
        	    int mapNum = monRs.getInt("map_id");
        	    int index = monRs.getInt("mon_id")-1 ;

        	    if (mapNum < gp.maxMap && index < gp.monster[mapNum].length && !monRs.getString("mon_name").equals("NA")) {
        	        Entity mon = gp.eGenerator.getMonster(monRs.getString("mon_name"));
        	       
        	        if (mon != null) {
        	            mon.worldX = monRs.getInt("world_x");
        	            mon.worldY = monRs.getInt("world_y");
        	            mon.life = monRs.getInt("life");
        	            mon.alive = monRs.getBoolean("alive");
        	            gp.monster[mapNum][index] = mon;
        	        }
        	    }
        	}

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkExistAccount(String userName) {
        boolean accountExists = false;
        String query = "SELECT COUNT(*) FROM account WHERE username = ?";
        try (Connection conn = dbManager.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    accountExists = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accountExists;
    }

    public boolean checkPlayerExists(int idaccount) {
        String checkSql = "SELECT 1 FROM player WHERE id = ? LIMIT 1";
        try (Connection conn = dbManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(checkSql)) {
            stmt.setInt(1, idaccount);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkPassword(String userName, String password) {
        boolean isPasswordCorrect = false;
        
        // Kiểm tra tài khoản có tồn tại không trước
        if (checkExistAccount(userName)) {
            String query = "SELECT password FROM account WHERE username = ?";
            try (Connection conn = dbManager.getConnection(); 
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, userName);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    
                    // So sánh mật khẩu nhập vào với mật khẩu lưu trong cơ sở dữ liệu
                    if (storedPassword.equals(password)) {
                        isPasswordCorrect = true;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isPasswordCorrect;
    }
    
    public void addAccount(String userName, String password) {
        String query = "INSERT INTO account (username, password) VALUES (?, ?)";
        try (Connection conn = dbManager.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userName);
            stmt.setString(2, password);
            stmt.executeUpdate();

            String IdQuery = "SELECT idaccount FROM account ORDER BY idaccount DESC LIMIT 1";
            try (PreparedStatement Idsttm = conn.prepareStatement(IdQuery);
                 ResultSet IdRs = Idsttm.executeQuery()) {
                if (IdRs.next()) {
                    gp.player.playerId = IdRs.getInt("idaccount");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Lỗi khi truy vấn id tài khoản: " + e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi thêm tài khoản: " + e.getMessage());
        }
    }

    

    public void updatePlayerId(String userName, String password) {
        String IdQuery = "SELECT idaccount FROM account WHERE username = ? AND password = ?";
        try (Connection conn = dbManager.getConnection(); 
             PreparedStatement Idsttm = conn.prepareStatement(IdQuery)) {
            
            // Gán giá trị cho các tham số trong truy vấn
            Idsttm.setString(1, userName);
            Idsttm.setString(2, password);
            
            try (ResultSet IdRs = Idsttm.executeQuery()) {
                if (IdRs.next()) {
                    gp.player.playerId = IdRs.getInt("idaccount");
                } else {
                    System.out.println("Không tìm thấy tài khoản với username và password được cung cấp.");
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi truy vấn id tài khoản: " + e.getMessage());
        }
    }
    
    public void reset() {
    	String deleteGamepanel = "DELETE FROM gamepanel WHERE id_panel = ?";
        String deleteInventory = "DELETE FROM inventory WHERE player_id = ?";
        String deleteObjects = "DELETE FROM map_objects WHERE player_id = ?";
        String deleteMonsters = "DELETE FROM monster WHERE player_id = ?";
        String deletePlayer = "DELETE FROM player WHERE id = ?";

        try (Connection conn = dbManager.getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu giao dịch

            try (PreparedStatement gamepanelStmt = conn.prepareStatement(deleteGamepanel);
            	 PreparedStatement inventoryStmt = conn.prepareStatement(deleteInventory);
                 PreparedStatement objectsStmt = conn.prepareStatement(deleteObjects);
                 PreparedStatement monstersStmt = conn.prepareStatement(deleteMonsters);
                 PreparedStatement playerStmt = conn.prepareStatement(deletePlayer);
                 Statement resetStmt = conn.createStatement()) {

                // Xóa dữ liệu liên quan
            	
            	gamepanelStmt.setInt(1, gp.player.playerId);
            	gamepanelStmt.executeUpdate();
            	
                inventoryStmt.setInt(1, gp.player.playerId);
                inventoryStmt.executeUpdate();

                objectsStmt.setInt(1, gp.player.playerId);
                objectsStmt.executeUpdate();

                monstersStmt.setInt(1, gp.player.playerId);
                monstersStmt.executeUpdate();

                // Xóa player
                playerStmt.setInt(1, gp.player.playerId);
                playerStmt.executeUpdate();

                conn.commit(); // Xác nhận giao dịch
            } catch (SQLException e) {
                conn.rollback(); // Hoàn tác nếu có lỗi
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
