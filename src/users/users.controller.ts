import { Body, Controller, Delete, Get, Post, Put, Req } from '@nestjs/common';
import { Request } from 'express';
import { UsersService } from './users.service';
import { User } from './interfaces/users.interface';
import { CreateUserDto } from './dto/create-user-dto';

@Controller('users')
export class UsersController {
    constructor(private usersService: UsersService) { }

    @Get()
    async findAll(@Req() request: Request): Promise<User[]> {
        console.log(request.query);
        return this.usersService.findAll();
    }

    @Get(':id')
    async findOne(@Req() request: Request): Promise<User | null> {
        return this.usersService.findOne(request.params.id);
    }

    @Post()
    async create(@Body() createUserDto: CreateUserDto): Promise<User> {
        return this.usersService.create(createUserDto);
    }

    @Put(':id')
    async update(@Req() request: Request, @Body() createUserDto: CreateUserDto): Promise<User | null> {
        return this.usersService.update(request.params.id, createUserDto);
    }

    @Delete(':id')
    async remove(@Req() request: Request): Promise<User | null> {
        return this.usersService.softDelete(request.params.id);
    }
}
