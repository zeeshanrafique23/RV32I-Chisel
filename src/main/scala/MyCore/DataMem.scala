package MyCore
import chisel3._

class DataMem extends Module{
    val io = IO(new Bundle{
        val address = Input(UInt(10.W))
        val dataIn   = Input(SInt(32.W))
        val readEn  = Input(UInt(1.W))
        val writeEn = Input(UInt(1.W))
        val dataOut = Output(SInt(32.W))
    })

    val mem = Mem(1024,SInt(32.W))
    
    io.dataOut := 0.S

    when (io.readEn === 1.U && io.writeEn === 0.U){
        io.dataOut := mem.read(io.address)
    }.elsewhen(io.readEn === 0.U && io.writeEn === 1.U){
        mem.write(io.address,io.dataIn)
    }

}