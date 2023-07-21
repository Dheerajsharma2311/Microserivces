package HDFCtest.Com;

import Com.java.encrypt_decrypt.decrypt;
import Com.java.pojo.Request;
import YTest.DataDecryption;
import YTest.Symmetric_Decryption;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	//DataDecryption dataDecryption=new DataDecryption();
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
  @POST
   @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    public String test(Request requestData) throws Exception

    {

    System.out.println(requestData.getRequestSignatureEncryptedValue()+"+"+requestData.getSymmetricKeyEncryptedValue());

      Symmetric_Decryption d=new Symmetric_Decryption();
     d.decryptSymmatrickey(requestData.getSymmetricKeyEncryptedValue());

      return  d.decryptSymmatrickey(requestData.getSymmetricKeyEncryptedValue());

        

    }
    }





