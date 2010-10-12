package caveexplorer.client;
public enum MessageType
{
/*KS Klient till server
//SK Server till klient */

   
   ORDER,  //KS skicka en order till servern
      MOVE,//SK något har flyttats
      IMPOSSIBLE_ORDER, //SK senaste ordern ogiltig
      CREATE_UNIT, //SK en enhet har skapats
      KILL, //SK något dog/kollapsade
      SHOOT, //SK spelare ser projektil
      MOVE_A, //KS be om att flytta enhet
      CREATE_UNIT_A, //KS ber om att skapa en enhet
      ABORT, //KS vill avbryta sin order
      SETCASH, //KS uppdatera cash
      NOMSG //NA Fanns inget mer i kön..
}
