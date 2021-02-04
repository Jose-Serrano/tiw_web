package es.uc3m.helpers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import es.uc3m.model.Item;
import es.uc3m.model.ItemPK;
import es.uc3m.model.Usuario;

public class ItemHelpers {
	public Item create_item(ItemPK key, String category, String buyer, String description, 
			byte[] image, String name, Usuario owner, int prize) {
		
		Item toReturn = new Item();
		toReturn.setImagen(image);
		toReturn.setCategoria(category);
		toReturn.setNombre(name);
		toReturn.setDescripcion(description);
		toReturn.setUsuario(owner);
		toReturn.setComprador(buyer);
		toReturn.setId(key);
		toReturn.setPrecio(prize);
		return toReturn;
	}
	
	public ItemPK generate_PK(int idItems, String owner) {
		ItemPK toReturn = new ItemPK();
		toReturn.setIditems(idItems);
		toReturn.setEmailDueño(owner);
		return toReturn;
	}
	
	public byte[] toByteArray(InputStream in) throws IOException {
		 
        ByteArrayOutputStream os = new ByteArrayOutputStream();
 
        byte[] buffer = new byte[1024];
        int len;
 
        // read bytes from the input stream and store them in buffer
        while ((len = in.read(buffer)) != -1) {
            // write bytes from the buffer into output stream
            os.write(buffer, 0, len);
        }
 
        return os.toByteArray();
    }
}
