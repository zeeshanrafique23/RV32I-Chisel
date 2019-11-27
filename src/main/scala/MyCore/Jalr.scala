package MyCore 
import chisel3._

class Jalr extends Module{
    val io = IO(new Bundle{
       val a = Input(SInt(32.W))
       val b = Input(SInt(32.W))
       val out = Output(UInt(32.W))
     })

     val sum = io.a + io.b
     io.out := (sum & 4294967294L.S).asUInt
}