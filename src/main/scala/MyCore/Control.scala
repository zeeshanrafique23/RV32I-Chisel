package MyCore
import chisel3._

class Control extends Module{
    val io = IO(new Bundle{
        val opcode = Input(UInt(5.W))

        val regWrite = Output(UInt(1.W))
        val memtoReg = Output(UInt(1.W))
        val memRead  = Output(UInt(1.W))
        val memWrite = Output(UInt(1.W))
        val branch   = Output(UInt(1.W))
        val oprndAsel= Output(UInt(2.W))
        val oprndBsel= Output(UInt(1.W))
        val immSel   = Output(UInt(2.W))
        val nextPCsel= Output(UInt(2.W))
        val aluOP    = Output(UInt(3.W))
    })

    val typeDecode   = Module(new TypeDecode())
    val controlDecode= Module(new ControlDecode())

    typeDecode.io.opcode    := io.opcode

    controlDecode.io.rtype  := typeDecode.io.rtype
    controlDecode.io.stype  := typeDecode.io.stype
    controlDecode.io.itype  := typeDecode.io.itype
    controlDecode.io.load   := typeDecode.io.load
    controlDecode.io.sbtype := typeDecode.io.sbtype
    controlDecode.io.jalr   := typeDecode.io.jalr
    controlDecode.io.ujtype := typeDecode.io.ujtype
    controlDecode.io.auipc  := typeDecode.io.auipc
    controlDecode.io.lui    := typeDecode.io.lui

    io.regWrite    := controlDecode.io.regWrite
    io.memtoReg    := controlDecode.io.memtoReg
    io.memRead     := controlDecode.io.memRead
    io.memWrite    := controlDecode.io.memWrite
    io.branch      := controlDecode.io.branch
    io.oprndAsel   := controlDecode.io.oprndAsel
    io.oprndBsel   := controlDecode.io.oprndBsel
    io.immSel      := controlDecode.io.immSel
    io.nextPCsel   := controlDecode.io.nextPCsel
    io.aluOP       := controlDecode.io.aluOP
}