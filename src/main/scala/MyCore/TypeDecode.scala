package MyCore
import chisel3._

class TypeDecode extends Module{
    val io = IO(new Bundle{
        val opcode = Input (UInt(5.W))

        val rtype  = Output(UInt())
        val itype  = Output(UInt())
        val load   = Output(UInt())
        val stype  = Output(UInt())
        val sbtype = Output(UInt())
        val lui    = Output(UInt())
        val auipc  = Output(UInt())
        val ujtype = Output(UInt())
        val jalr   = Output(UInt())
    })

        io.rtype  := 0.U
        io.itype  := 0.U
        io.load   := 0.U
        io.stype  := 0.U
        io.sbtype := 0.U
        io.lui    := 0.U
        io.auipc  := 0.U
        io.ujtype := 0.U
        io.jalr   := 0.U

    when  (io.opcode === 12.U){
        io.rtype  := 1.U
    } .elsewhen (io.opcode ===  4.U){
        io.itype  := 1.U
    } .elsewhen (io.opcode ===  0.U){
        io.load   := 1.U
    } .elsewhen (io.opcode ===  8.U){
        io.stype  := 1.U
    } .elsewhen (io.opcode === 24.U){
        io.sbtype := 1.U
    } .elsewhen (io.opcode === 13.U){
        io.lui    := 1.U
    } .elsewhen (io.opcode ===  5.U){
        io.auipc  := 1.U
    } .elsewhen (io.opcode === 27.U){
        io.ujtype := 1.U
    } .elsewhen (io.opcode === 25.U){
        io.jalr   := 1.U
    }
}